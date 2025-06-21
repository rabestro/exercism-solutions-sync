#!/usr/bin/env bash

readonly power=${#1}
readonly number=$1

sum=0
while read -rn 1 digit; do
  (( sum += digit ** power ))
done <<< "$number"

if ((sum == number)); then
  echo true
else
  echo false
fi
