#!/usr/bin/env bash

die () {
    echo "$1"
    exit 1
}
one () {
    echo 1
    exit
}

check_for_report_one () {
    (( $# == 1 )) || (( $2 == 0 )) && one
}

validate_args () {
    (( ${#1} < $2 )) && die "span must be smaller than string length"
    [[ $1 == *[^[:digit:]]* ]] && die "input must only contain digits"
    (( $2 < 0 )) && die "span must not be negative"
}

print_max_product () {
    local -r number="$1"
    local -ir span="$2"
    local -i product digit max_product i k
    local -i max_index=$(( ${#number} - span + 1 ))

    for (( i = 0; i < max_index; ++i ))
    do
        digit=${number:${i}:1}
        (( product = digit ))
        for (( k = 1; k < span; ++k ))
        do
           digit=${number:$((i + k)):1}
           (( product *= digit ))
        done
        (( max_product = product > max_product ? product : max_product ))
    done

    echo "$max_product"
}

main () {
    check_for_report_one "$@"
    validate_args "$@"
    print_max_product "$@"
}

main "$@"
