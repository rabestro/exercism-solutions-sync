{
    for (i = 1; i <= NF; ++i) $i = pigify($i)
    print
}

function pigify(word,   part) {
    if (word ~ /^([aouei]|xr|yt)/) return word"ay"
    match(word, /^([^aouei]?qu|[^aouei][^aoueiy]*)?(.*)/, part)
    return part[2] part[1] "ay"
}
