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
{
    score = $1
}
/allergic_to/ {
    item = Items[$3]
    for (i = 128; i > item; i /= 2)
        if (score >= i) score -= i
    print score >= item ? "true" : "false"
}
/list/ {
    NF = 0
    PROCINFO["sorted_in"] = "@val_num_asc"
    for (item in Items) if (and(score, Items[item])) $(++NF) = item
    print
}
