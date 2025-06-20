BEGIN {
    FS = "[^[:alnum:]']"
}
{
    for (word = 1; word <= NF; ++word) {
        printf "%c", toupper($word)
    }
    printf "\n"
}
