BEGIN {
    FS = "|"
    OFS = ""
    Alphabet = "abcdefghijklmnopqrstuvwxyz"
    M = length(Alphabet)
}
{
    Cryption = $1
    A = $2
    B = $3
    Message = $4
}
gcd(A, M) != 1 {
    print "a and m must be coprime.";
    exit 1
}
{
    NF = 0
    for (i = 1; i <= length(Message); ++i) {
        symbol = substr(Message, i, 1)
        if (symbol !~ /[[:alnum:]]/) continue
        if (Cryption == "encode" && NF && (1 + NF) % 6 == 0) $(++NF) = " "
        $(++NF) = code(symbol, Cryption)
    }
    print
}

function code(symbol, f,   y) {
    if (symbol ~ /[[:digit:]]/) return symbol
    y = index(Alphabet, tolower(symbol)) - 1

    return substr(Alphabet, @f(y), 1)
}
function encode(y) {
    return 1 + (A * y + B) % M
}
function decode(y,   i,mmi) {
    for (i = 0; !mmi && i < M; i++)
        if (A * i % M == 1) mmi = i
    return 1 + mmi * (2 * M + y - B) % M
}
function gcd(p,q){return(q?gcd(q,(p%q)):p)}
