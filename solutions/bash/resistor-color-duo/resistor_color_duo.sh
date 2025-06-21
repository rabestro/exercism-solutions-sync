#!/usr/bin/env bash

color_number() {
  declare -A colors=(
    ["black"]=0 ["brown"]=1 ["red"]=2 ["orange"]=3 ["yellow"]=4
    ["green"]=5 ["blue"]=6 ["violet"]=7 ["grey"]=8 ["white"]=9
  )
  if [[ -z ${colors[$1]} ]]; then
    echo "invalid color" >&2
    return 1
  fi
  echo "${colors[$1]}"
}

a=$(color_number "$1") || exit 1
b=$(color_number "$2") || exit 1
echo "$a$b"
