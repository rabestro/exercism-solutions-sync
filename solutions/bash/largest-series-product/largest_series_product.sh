#!/usr/bin/env bash

die () {
  echo "$1"
  exit 1
}
one () {
  echo 1
  exit
}

(( $# == 1 )) || (( $2 == 0 )) && one

readonly number="$1"
readonly span="$2"

(( ${#number} < $2 )) && die "span must be smaller than string length"
[[ $1 = *[^[:digit:]]* ]] && die "input must only contain digits"
(( span < 0 )) && die "span must not be negative"

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
