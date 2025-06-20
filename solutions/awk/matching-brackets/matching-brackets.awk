BEGIN {
    MatchingBrackets = @/\[]|\()|\{}|[^][(){}]+/
}
{
    while (gsub(MatchingBrackets, ""));
    print $0 ? "false" : "true"
}
