#!/usr/bin/env bash

die () {
  echo "$1"; exit 1
}

one () {
  echo 1; exit
}

digit () {
  echo $(($1 % 10 ** $2 / 10 ** ($2 - 1)  ))
}

(( $# == 1 )) || (( $2 == 0 )) && one

readonly number="$1" span="$2"

(( ${#number} < $2 )) && die "span must be smaller than string length"
[[ $1 = *[^[:digit:]]* ]] && die "input must only contain digits"
(( span < 0 )) && die "span must not be negative"

declare -i max_index="${#number} - $span + 1" product

for i in {1..$max_index}
do
    (( product = ${number:${i}:1} ))
    for (( k = 1; k < span; ++k ))
    do
       (( l = i + k, product *= ${number:${l}:1} ))
    done

    if (( product > max_product ))
    then
        max_product=product
    fi
done

echo $max_product
exit

bc <<< "
number = $number
span = $span

define digit(i) {
    return number % 10^i / 10^--i
}

max_index = length(number) - span + 1

for (i = 1; i <= max_index; ++i) {
    product = digit(i)
    for (k = 1; k < span; ++k) {
        product *= digit(i + k)
    }
    if (product > max_product) {
        max_product = product
    }
}

print max_product"
