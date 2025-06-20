BEGIN {
    FPAT = "[.*]"
    OFS = ""
}
{
    for (col = NF; col; --col)
        Field[NR, col] = $col == "*"
}
END {
    while (row++ < NR) {
        for (col = NF; col; --col)
            $col = print_cell(row, col)
        print
    }
}

function print_cell(row, col,   mines,i) {
    if (Field[row, col]) return "*"
    for (i = 8; i; --i)
        mines += Field[row + int(i / 3) - 1, col + i % 3 - 1]
#    mines = Field[row - 1, col - 1] \
# + Field[row - 1, col] \
# + Field[row - 1, col + 1] \
# + Field[row, col - 1] \
# + Field[row, col + 1] \
# + Field[row + 1, col - 1] \
# + Field[row + 1, col] \
# + Field[row + 1, col + 1]
    return mines ? mines : "."
}