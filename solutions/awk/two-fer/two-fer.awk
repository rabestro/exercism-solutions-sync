{
    if (!$0) {
        $0 = "you"
    }
    print "One for "$0", one for me."
}
END {
    if (!NR) {
        print "One for you, one for me."
    }
}