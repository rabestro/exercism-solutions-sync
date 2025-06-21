#!/usr/bin/env bash

main () {
  local -ir rows=$1
  local -a triangle=(1) numbers
  local -i ident=rows

  for ((i=0; i<rows; i++)); do
    numbers=(1)
    for ((j=1; j<=i; j++)); do
      numbers+=( $((triangle[j-1] + triangle[j])) )
    done
    printf "%$((--ident))s" ''
    echo "${numbers[@]}"
    triangle=("${numbers[@]}")
  done
}

main "$@"
