BEGIN {
    OFS = FS = ","
    Allergens["eggs"] = 1
    Allergens["peanuts"] = 2
    Allergens["shellfish"] = 4
    Allergens["strawberries"] = 8
    Allergens["tomatoes"] = 16
    Allergens["chocolate"] = 32
    Allergens["pollen"] = 64
    Allergens["cats"] = 128
    PROCINFO["sorted_in"] = "@val_num_asc"
}
{
    score = $1
}
/allergic_to/ {
    print is_allegric(score, $3) ? "true" : "false"
}
/list/ {
    NF = 0
    for (allergen in Allergens)
        if (is_allegric(score, allergen))
            $++NF = allergen
    print
}
function is_allegric(score, name) {
    return and(score, Allergens[name])
}
