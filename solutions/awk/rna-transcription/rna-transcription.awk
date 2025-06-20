BEGIN {
    OFS = FS = ""
    T["G"] = "C"
    T["C"] = "G"
    T["T"] = "A"
    T["A"] = "U"
}
/[^GCTA]/ {
    print "Invalid nucleotide detected."; exit 1
}
{
    while (i++ < NF) $i = T[$i]
    print
}
