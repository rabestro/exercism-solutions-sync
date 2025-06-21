#!/usr/bin/env bash

declare -Ar digits=( \
    [" _ | ||_|   "]=0 ["     |  |   "]=1 [" _  _||_    "]=2 [" _  _| _|   "]=3 ["   |_|  |   "]=4 \
    [" _ |_  _|   "]=5 [" _ |_ |_|   "]=6 [" _   |  |   "]=7 [" _ |_||_|   "]=8 [" _ |_| _|   "]=9 )

die () {
    echo "$1"
    exit 1
}

parse_line () {
    for (( col = 0; col < ${#1}; col += 3 ))
    do
        echo -n "${digits["${1:$col:3}${2:$col:3}${3:$col:3}${4:$col:3}"]:-?}"
    done
}

main () {
    [[ -t 0 ]] && echo '' && exit

    readarray -t lines
    (( ${#lines[@]} % 4 )) && die "Number of input lines is not a multiple of four"
    (( ${#lines[0]} % 3 )) && die "Number of input columns is not a multiple of three"

    local -i row=0
    while (( row < ${#lines[@]} ))
    do
        (( row )) && echo -n ','
        parse_line "${lines[$((row++))]}" "${lines[$((row++))]}" "${lines[$((row++))]}" "${lines[$((row++))]}"
    done
}

main "$@"
