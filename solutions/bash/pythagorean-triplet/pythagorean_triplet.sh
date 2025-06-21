#!/usr/bin/env bash
declare -ri SMALLEST_POSSIBLE_A=3

main () {
  local -ri sum="$1"
  ((sum % 2 == 0)) || return 0

  local -ri limit=$(( sum / 3 ))
  local -i a b c numerator denominator remainder

  for (( a=SMALLEST_POSSIBLE_A; a <= limit; a++ ))
  do
      (( numerator = sum * sum - 2 * sum * a ))
      (( denominator = 2 * (sum - a) ))
      (( remainder = numerator % denominator ))
      (( remainder == 0 )) || continue
      (( b = numerator / denominator ))
      (( a < b )) || continue
      (( c = sum - a - b ))
      (( c > b )) || return 0
      printf "%d,%d,%d\n" $a $b $c
  done
  return 0
}

main "$@"
