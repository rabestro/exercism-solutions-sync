function print_triangle(rows,   i) {
    if (rows < 1) return
    $1 = NF = 1
    do {
        print
        for (i = ++NF; i > 1; --i) $i += $(i - 1)
    } while (NF <= rows)
}
{
    print_triangle($1)
}