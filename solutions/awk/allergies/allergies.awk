BEGIN {
    OFS = FS = ","
    Items["eggs"] = 1
    Items["peanuts"] = 2
    Items["shellfish"] = 4
    Items["strawberries"] = 8
    Items["tomatoes"] = 16
    Items["chocolate"] = 32
    Items["pollen"] = 64
    Items["cats"] = 128
}
/allergic_to/ {
    print and($1, Items[$3]) ? "true" : "false"
}
/list/ {
    score = $1
    NF = 0
    PROCINFO["sorted_in"] = "@val_num_asc"
    for (item in Items) if (and(score, Items[item])) $(++NF) = item
    print
}
