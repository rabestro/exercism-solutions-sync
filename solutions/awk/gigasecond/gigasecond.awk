BEGIN {
    GigaSecond = 1e9
    FS = "[-T:]"
}
NF == 6 {
    $4 = $4 - 1
}
NF == 3 {
    $4 = "01"
    $5 = $6 = "00"
}
{
    time = mktime($1" "$2" "$3" "$4" "$5" "$6) + GigaSecond
    print strftime("%FT%T", time)
}
