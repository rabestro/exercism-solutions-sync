BEGIN {
    FS = "[^[:alnum:]']"
    OFS = ""
}
{
    for (i = NF; i; --i)
        $i = toupper(substr($i, 1, 1))
}
1
