#!/usr/bin/env bash

readonly re_word="([[:alpha:]])['[:alpha:]]*[^[:alpha:]]*"
phrase=$1

while [[ $phrase =~ $re_word ]]; do
  acronym+=${BASH_REMATCH[1]}
  phrase=${phrase#${BASH_REMATCH[0]}}
done

echo "${acronym^^}"