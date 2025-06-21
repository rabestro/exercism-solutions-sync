#!/usr/bin/env bash
declare -ri SMALLEST_POSSIBLE_A=3

print_optional_triplet() {
  local -ri sum="$1"
  local -ri a="$2"
  local -ri numerator=$(( sum * sum - 2 * sum * a ))
  local -ri denominator=$(( 2 * (sum - a) ))
  local -ri remainder=$(( numerator % denominator ))
  (( remainder == 0 )) || return 0
  local -ri b=$(( numerator / denominator ))
  (( a < b )) || return 0
  local -ri c=$(( sum - a - b ))
  printf "%d,%d,%d\n" $a $b $c
}

main () {
  local -ri sum="$1"
  ((sum % 2 == 0)) || return 0

  local -ri limit=$(( sum / 3 ))
  for (( i=SMALLEST_POSSIBLE_A; i <= limit; i++ ))
  do
    print_optional_triplet "$sum" "$i"
  done
  return 0
}

main "$@"