BEGIN {
    RnaTranscription = "tr GCTA CGAU"
}
/[^GCTA]/ {
    print "Invalid nucleotide detected."; exit 1
}
{
    print | RnaTranscription
}
END {
    close(RnaTranscription)
}
