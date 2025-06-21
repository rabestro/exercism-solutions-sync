#!/usr/bin/env bash
declare -ri SMALLEST_POSSIBLE_A=3

find_pythagorean_triplets () {
  local -ri sum="$1"
  (( sum % 2 == 0 )) || return 0
  local -i a b c numerator denominator

  for (( a = SMALLEST_POSSIBLE_A; 1; a++ ))
  do
      (( numerator = sum * sum - 2 * sum * a ))
      (( denominator = 2 * (sum - a) ))
      (( b = numerator / denominator ))
      (( b * denominator == numerator )) || continue
      (( a < b )) || break
      (( c = sum - a - b ))
      printf "%d,%d,%d\n" $a $b $c
  done
}

find_pythagorean_triplets "$@"
