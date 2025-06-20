/[[:digit:]]+/ {
    Score[++Size] = $1
    if ($1 > MaxScore) MaxScore = $1
}
/list/ {
    list()
}
/latest/ {
    print Score[Size]
}
/personalBest/ {
    top(1)
}
/personalTopThree/ {
    top(3)
}

function list(   i) {
    PROCINFO["sorted_in"] = "@ind_num_asc"
    for (i in Score) print Score[i]
}

function top(n,   i) {
    PROCINFO["sorted_in"] = "@val_num_desc"
    for (i in Score)
        if (!n--) break
        else print Score[i]
}
