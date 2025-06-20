BEGIN {
    IGNORECASE = 1
    Alphabet = "abcdefghijklmnopqrstuvwxyz"
}
{
    print isPangram() ? "true" : "false"
}
function isPangram() {
    return !length(gensub("[ "$0"]", "", "g", Alphabet))
}
