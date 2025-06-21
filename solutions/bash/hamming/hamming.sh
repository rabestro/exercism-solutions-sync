#!/usr/bin/env bash

if (( $# != 2 )); then
  echo "Usage: hamming.sh <string1> <string2>"
  exit 1
fi

declare -r first=$1
declare -r second=$2

if (( ${#first} != ${#second} )); then
  echo "left and right strands must be of equal length"
  exit 1
fi

declare -i difference=0

for ((i = 0; i < ${#first}; i++)); do
  if [[ ${first:$i:1} != "${second:$i:1}" ]]; then
    ((difference++))
  fi
done

echo "$difference"
