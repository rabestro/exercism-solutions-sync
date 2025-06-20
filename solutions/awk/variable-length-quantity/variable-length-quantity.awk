@include "assert"
# These variables are initialized on the command line (using '-v'):
# - action

BEGIN {
    assert(action ~ /(en|de)code/, "unknown action")
}

action == "encode" {
    for (i = 1; i <= NF; ++i) $i = encode($i)
    print
}

action == "decode" {
    for (i = 1; i <= NF; ++i) {
        hex = strtonum("0x"$i)
        if (hex > 127) {
            number = number * 128 + hex - 128
        } else {
            $(++k) = sprintf("%02X", number * 128 + hex)
            number = 0
        }
    }
    NF = k
    assert(NF && !number, "incomplete byte sequence")
    print
}

function encode(number,   out,hex) {
    number = strtonum("0x"number)
    do {
        hex = out ? 128 : 0
        hex += number % 128
        out = sprintf("%02X", hex) (out ? " " out : "")
        number = int(number / 128)
    } while(number)

    return out
}
