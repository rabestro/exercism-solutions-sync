#!/usr/bin/env bash

die () {
    echo "$1"; exit 1
}
one () {
    echo 1; exit
}

(( $# == 1 )) || (( $2 == 0 )) && one

readonly number="$1" span="$2"

(( ${#number} < $2 )) && die "span must be smaller than string length"
[[ $1 == *[^[:digit:]]* ]] && die "input must only contain digits"
(( span < 0 )) && die "span must not be negative"

declare -i max_index="${#number} - $span + 1" product max_product=0 digit

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

echo $max_product
