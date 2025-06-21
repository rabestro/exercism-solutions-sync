#!/usr/bin/env bash

readonly phrase=${1//[!a-zA-Z?]/}

if [[ $1 =~ ^[[:space:]]*$ ]]; then
  response="Fine. Be that way!"
elif [[ $phrase =~ ^[[:upper:]]+\?$ ]]; then
  response="Calm down, I know what I'm doing!"
elif [[ $phrase =~ ^[[:upper:]]+$ ]]; then
  response="Whoa, chill out!"
elif [[ $phrase =~ \?$ ]]; then
  response="Sure."
else
  response="Whatever."
fi
printf '%s' "$response"
