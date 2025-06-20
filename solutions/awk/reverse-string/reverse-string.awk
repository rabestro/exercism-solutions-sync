BEGIN {
    FS=""
}
{
    for ( ; NF > 0; --NF) {
        printf $(NF)
    }
    print ""
}
