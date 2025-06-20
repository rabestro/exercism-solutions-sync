BEGIN {
    Command = "tr GCTA CGAU"
}
/[^GCTA]/ {
    print "Invalid nucleotide detected."; exit 1
}
{
    print | Command
}
