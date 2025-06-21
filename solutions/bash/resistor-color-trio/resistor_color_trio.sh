#!/usr/bin/env bash

validate() {
  if [[ -z "$1" ]]; then
    echo "invalid colour" >&2
    exit 1
  fi
}

declare -A colors=(
  ["black"]=0 ["brown"]=1 ["red"]=2 ["orange"]=3 ["yellow"]=4
  ["green"]=5 ["blue"]=6 ["violet"]=7 ["grey"]=8 ["white"]=9)

suffix=(" " "0 " "00 " " kilo" "0 kilo" "00 kilo" " mega" "0 mega" "00 mega" " giga")

a=${colors[$1]}
validate "$a"

b=${colors[$2]}
validate "$b"

c=${colors[$3]}
validate "$c"

if ((b == 0)) && ((a != 0)); then
  unset b
  ((c++))
fi
((a == 0)) && unset a

echo "${a}${b}${suffix[$c]}ohms"
