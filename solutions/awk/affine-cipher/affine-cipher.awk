BEGIN {
    FS = "|"
    Alphabet = "abcdefghijklmnopqrstuvwxyz"
    M = length(Alphabet)
}

!coprime() {
    print "a and m must be coprime."; exit 1
}

$1 == "encode" {
    for (i = 1; i <= length($4); ++i) {
        symbol = substr($4, i, 1)
        if (symbol !~ /[[:alnum:]]/) continue
        if (out && (1 + length(out)) % 6 == 0) out = out " "
        out = out E(symbol)
    }
    print out
}

$1 == "decode" {
    for (i = 1; i <= length($4); ++i) {
        symbol = substr($4, i, 1)
        if (symbol !~ /[[:alnum:]]/) continue
        out = out D(symbol)
    }
    print out
}

function E(x,   i,e) {
    if (x ~ /[[:digit:]]/) return x
    i = index(Alphabet, tolower(x)) - 1
    e = 1 + ($2 * i + $3) % M
    return substr(Alphabet, e, 1)
}
function D(symbol,   e,letter,i,a_inv,y) {
    if (symbol !~ /[[:alpha:]]/) return symbol

    y = index(Alphabet, tolower(symbol)) - 1

    for (i = 0; i < M; i++) {
        if (($2 * i) % M == 1) {
            a_inv = i
        }
    }

    e = a_inv * (2 * M + y - $3) % M
    letter = substr(Alphabet, e + 1, 1)
    return letter
}
function coprime() {return gcd($2, M)==1}
function gcd(p,q){return(q?gcd(q,(p%q)):p)}
