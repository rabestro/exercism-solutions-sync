{
    rows = $0
    for ($0 = 1; NF <= rows;) {
        print
        for (i = ++NF; i > 1; --i) $i += $(i - 1)
    }
}