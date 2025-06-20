!$0 {
    print "series cannot be empty"; exit 1
}
len <= 0 || length($0) < len {
    print "invalid length"; exit 1
}
{
    n = $0
    while (i++ <= length(n) - len) $i = substr(n, i, len)
    print
}
