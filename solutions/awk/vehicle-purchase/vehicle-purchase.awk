BEGIN {
    FS = ","    # comma is the field separator
}

$1 == "needs_license" && $2 ~ /car|truck/ {
    print "true"
}

$1 == "resell_price" {
    factor = $3 < 3 ? 0.8 : $3 <= 10 ? 0.7 : 0.5
    print $2 * factor
}
