#!/usr/bin/env bash

false() {
  echo "false"
  exit 0
}

main() {
  local isbn=${1//-/}
  [[ $isbn =~ ^[[:digit:]]{9}[[:digit:]X]$ ]] || false

  local -i i=10 sum=0
  while ((i > 1)); do
    ((sum += ${isbn: -i:1} * i--))
  done

  local check=${isbn: -1}
  ((sum += ${check/X/10}))

  ((sum % 11)) && false
  echo "true"
}

main "$@"
