#!/usr/bin/env bash

readonly target=$1
shift
readonly array=("$@")

declare -i left=0
declare -i right=${#array[@]}

while ((left < right)); do
  mid=$(((left + right) / 2))
  if ((array[mid] > target)); then
    right=$((--mid))
  elif ((array[mid] < target)); then
    left=$((++mid))
  else
    break
  fi
done

echo $((array[mid] == target ? mid : -1))
