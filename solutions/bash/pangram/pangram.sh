#!/usr/bin/env bash

readonly sentence=${1//[!a-zA-Z]/}
readonly unique=$(echo "${sentence^^}" | grep -o . | sort | uniq | tr -d "\n")

if ((${#unique} == 26)); then
  echo true
else
  echo false
fi
