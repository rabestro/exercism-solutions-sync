#!/usr/bin/env bash

die () {
    echo "$1"
    exit 1
}

parse_digit () {
    case "$1" in
    " _ | ||_|   ") echo -n 0 ;;
    "     |  |   ") echo -n 1 ;;
    " _  _||_    ") echo -n 2 ;;
    " _  _| _|   ") echo -n 3 ;;
    "   |_|  |   ") echo -n 4 ;;
    " _ |_  _|   ") echo -n 5 ;;
    " _ |_ |_|   ") echo -n 6 ;;
    " _   |  |   ") echo -n 7 ;;
    " _ |_||_|   ") echo -n 8 ;;
    " _ |_| _|   ") echo -n 9 ;;
    *) echo -n '?' ;;
    esac
}

parse_line () {
    for (( col = 0; col < ${#1}; col += 3 ))
    do
        parse_digit "${1:$col:3}${2:$col:3}${3:$col:3}${4:$col:3}"
    done
}

[[ -t 0 ]] && echo '' && exit

readarray -t lines
(( ${#lines[@]} % 4 )) && die "Number of input lines is not a multiple of four"
(( ${#lines[0]} % 3 )) && die "Number of input columns is not a multiple of three"

for (( row = 0; row < ${#lines[@]}; row += 4 ))
do
    (( row )) && echo -n ','
    parse_line "${lines[$row]}" "${lines[$((row + 1))]}" "${lines[$((row + 2))]}" "${lines[$((row + 3))]}"
done
