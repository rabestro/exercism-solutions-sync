BEGIN {
    FPAT = "[][(){}]"
    Opposite["("] = ")"
    Opposite["["] = "]"
    Opposite["{"] = "}"
    OpenBracket = @/[[{(]/
}
{
    print isMatchingBrackets() ? "true" : "false"
}

function isMatchingBrackets(   i) {
    for (i = 1; i <= NF; ++i)
        if ($i ~ OpenBracket) push($i)
        else if ($i != Opposite[pop()]) return 0

    return isStackEmpty()
}

function push(element) {
    Stack[++Size] = element
}

function pop() {
    return Stack[Size--]
}

function isStackEmpty() {
    return Size == 0
}
