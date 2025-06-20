BEGIN {
    FPAT="[.*]"
    OFS = ""
}
NR == 1 {
    Width = NF
}
{
    for (col = NF; col; --col)
        Field[NR, col] = $col == "*"
}
END {
    while (row++ < NR) {
        for (col = Width; col; --col)
            $col = mines(row, col)
        print
    }
}

function mines(row, col,   count) {
    if (Field[row, col]) return "*"
    count = Field[row - 1, col - 1] \
        + Field[row - 1, col] \
        + Field[row - 1, col + 1] \
        + Field[row, col - 1] \
        + Field[row, col + 1] \
        + Field[row + 1, col - 1] \
        + Field[row + 1, col] \
        + Field[row + 1, col + 1]
    return count ? count : "."
}