#!/usr/bin/env bash

readonly power=${#1}
readonly number=$1

sum=0
for digit in $(fold -w1 <<<"$number"); do
  ((sum += digit ** power))
done

if ((sum == number)); then
  echo true
else
  echo false
fi
