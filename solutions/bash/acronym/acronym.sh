#!/usr/bin/env bash

main () {
  readonly words="$1"

  IFS=$' -,_*'
  for word in $words; do
    acronym+=${word:0:1}
  done

  echo "${acronym^^}"
}

main "$@"