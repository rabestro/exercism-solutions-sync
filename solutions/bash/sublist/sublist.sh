#!/usr/bin/env bash

main() {
  local -r a=${1//[][, ]/|} b=${2//[][, ]/|}
  [[ "$a" == "$b" ]] && echo "equal" && exit
  [[ "$a" == *"$b"* ]] && echo "superlist" && exit
  [[ "$b" == *"$a"* ]] && echo "sublist" && exit
  echo "unequal"
}

main "$@"