# These variables are initialized on the command line (using '-v'):
# - len

BEGIN {
    FPAT = "[[:digit:]]+|."
}
{
    @type()
}
function encode(   i,previous,count,out) {
    for (i = 1; i <= NF; ++i) {
        if (previous == $i) {
            ++count
            continue
        }
        out = out (count > 1 ? count : "") previous
        previous = $i
        count = 1
    }
    print out (count > 1 ? count : "") previous
}

function decode(   out,i,n) {
    for (i = 1; i <= NF; ++i)
        for (n = $i ~ /[[:digit:]]+/ ? $(i++) : 1; n > 0; --n)
            out = out $i
    print out
}
