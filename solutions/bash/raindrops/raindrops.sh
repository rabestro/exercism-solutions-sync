#!/usr/bin/env bash

main() {
  local -ir number="$1"
  local sound
  (( number % 3 == 0 )) && sound=Pling
  (( number % 5 == 0 )) && sound+=Plang
  (( number % 7 == 0 )) && sound+=Plong
  echo "${sound:-$number}"
}

main "$@"
