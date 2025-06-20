BEGIN {
    FPAT = "[[:digit:]]+|[[:alpha:]]+('[[:alpha:]]+)?"
    OFS = ": "
}
{
    $0 = tolower($0)
    for (i = 1; i <= NF; ++i) Words[$i]++
}
END {
    for (word in Words) print word, Words[word]
}
