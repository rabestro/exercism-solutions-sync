BEGIN {OFS=FS = ""}
{
    for (i = int(NF / 2); i; --i)
        swap(i, NF - i + 1)
    print
}

function swap(a, b,   t) {
    t = $a
    $a = $b
    $b = t
}
