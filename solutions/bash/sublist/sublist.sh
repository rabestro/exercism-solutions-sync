#!/usr/bin/env bash

main() {
  local -r a=${1//[][, ]/|} b=${2//[][, ]/|}
  if [[ "$a" == "$b" ]]; then
    echo "equal"
  elif [[ "$a" == *"$b"* ]]; then
    echo "superlist"
  elif [[ "$b" == *"$a"* ]]; then
    echo "sublist"
  else
    echo "unequal"
  fi
}

main "$@"