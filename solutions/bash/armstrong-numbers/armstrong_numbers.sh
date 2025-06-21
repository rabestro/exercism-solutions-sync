#!/usr/bin/env bash

set -euo pipefail

readonly power=${#1}
readonly number=$1
declare -i sum=0 digit

while read -rn 1 digit; do
  (( sum += digit ** power )) || true
done <<< "$number"

if ((sum == number)); then
  echo true
else
  echo false
fi
