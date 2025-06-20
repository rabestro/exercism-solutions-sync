@load "ordchr"

# These variables are initialized on the command line (using '-v'):
# - distance

BEGIN {
    FS = OFS = ""
}
{
    for (i = 1; i <= length($0); ++i) out = out encode(substr($0, i, 1))
    print out
}

function encode(letter) {
    if (letter ~ /[[:lower:]]/) return shift(letter, "a")
    if (letter ~ /[[:upper:]]/) return shift(letter, "A")
    return letter
}

function shift(l, a) {
    return chr(ord(a) + (ord(l) - ord(a) + distance) % 26)
}
