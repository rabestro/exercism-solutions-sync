function print_triangle(rows,   i,last) {
    if (rows < 1) return
    $1 = NF = 1
    do {
        print
        for (i = 1; i <= NF; ++i) last[i] = $i
        for (i = ++NF; i > 0; --i) $i = last[i - 1] + last[i]
    } while (NF <= rows)
}
{
    print_triangle($1)
}