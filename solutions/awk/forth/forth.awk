BEGIN {
    Number = @/^-?[[:digit:]]+$/
    # Built-in words are stored under internal names, so that a definition
    # keeps using them even after the user redefines the public name.
    split("+ - * / dup drop swap over", names)
    for (i in names) BuiltIn[names[i]] = "@" names[i]
}

BEGINFILE {
    delete Stack
    delete Macro
    Size = 0
}
{
    $0 = tolower($0)
}
$1 == ":" {
    if ($NF != ";") die("macro not terminated with semicolon")
    if (NF < 4) die("empty macro definition")
    if ($2 ~ Number) die("illegal operation")
    Macro[$2] = resolve(3, NF - 1)
    next
}
{
    $0 = resolve(1, NF)
    for (i = 1; i <= NF; ++i) execute($i)
}

ENDFILE {
    NF = Size
    while (Size) {
        $Size = Stack[Size]
        --Size
    }
    print
}

function die(message) {print message > "/dev/stderr"; exit 1}

# Replaces the words in fields first..last with what they mean right now:
# numbers stay as they are, user-defined words become their bodies,
# built-in words become their internal names.
function resolve(first, last,   i, resolved) {
    for (i = first; i <= last; ++i) {
        if ($i ~ Number) resolved = resolved " " $i
        else if ($i in Macro) resolved = resolved " " Macro[$i]
        else if ($i in BuiltIn) resolved = resolved " " BuiltIn[$i]
        else die("undefined operation")
    }
    return resolved
}

function execute(operation,   a, b) {
    if (operation ~ Number) {
        push(operation + 0)
        return
    }
    switch (operation) {
        case "@dup":
            required(1)
            a = pop()
            push(a); push(a)
            return
        case "@drop":
            required(1)
            pop()
            return
    }
    required(2)
    b = pop()
    a = pop()
    switch (operation) {
        case "@+": push(a + b); break
        case "@-": push(a - b); break
        case "@*": push(a * b); break
        case "@/":
            if (!b) die("divide by zero")
            push(int(a / b)); break
        case "@swap": push(b); push(a); break
        case "@over": push(a); push(b); push(a); break
    }
}

function required(n) {
    if (!Size) die("empty stack")
    if (n > 1 && Size == 1) die("only one value on the stack")
}
function push(element) {
    Stack[++Size] = element
}
function pop() {
    return Stack[Size--]
}
