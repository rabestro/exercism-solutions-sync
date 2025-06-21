#!/usr/bin/env bash

die () { echo "$1" >&2 ; exit 1; }

convert_base() {
  local -ir source_base="$1"
  local -ir target_base="$3"
  local -a digits=($2)
  local -i power="${#digits[@]}"
  local -i number=0
  local -i digit

  (( source_base > 1 )) || die "input base is incorrect"
  (( target_base > 1 )) || die "output base is incorrect"

  for digit in "${digits[@]}"; do
    (( digit >= 0 && digit < source_base )) || die "digit is incorrect"
    ((number += source_base ** --power * digit))
  done

  ((number)) && digits=() || digits=(0)

  while ((number)); do
    (( digit = number % target_base ))
    (( number /= target_base ))
    digits=("$digit" "${digits[@]}")
  done
  
  echo "${digits[@]}"
}

convert_base "$@"
