/total/ {
    print 2 ^ 64 - 1; next
}
{
    square = $1
}
1 <= square && square <= 64 {
    print 2 ^ ($1 - 1)
    next
}
{
    print "square must be between 1 and 64" > "/dev/stderr"
    exit 1
}
