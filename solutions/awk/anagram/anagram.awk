# These variables are initialized on the command line (using '-v'):
# - key
BEGIN {
    Word = toupper(key)
    split(Word, Letters, //)
    asort(Letters)
}
toupper($0) == Word || length($0) != length(Word) {next}

isAnagram() {
    print
}

function isAnagram(   candidate,i) {
    split(toupper($0), candidate, //)
    asort(candidate)
    for (i in Letters)
        if (candidate[i] != Letters[i]) return 0
    return 1
}
