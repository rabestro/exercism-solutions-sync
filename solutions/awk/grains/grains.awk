$0 == "total" {
    print "18446744073709551615"
    next
}
isOutOfRange() {
    print "square must be between 1 and 64"
    exit 1
}
{
    print 2 ^ --$0
}

function isOutOfRange() {return $0 < 1 || $0 > 64}
