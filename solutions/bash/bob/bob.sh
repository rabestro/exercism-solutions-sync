#!/usr/bin/env bash

readonly phrase=${1//[!a-zA-Z?]/}

if [[ $1 =~ ^[[:space:]\\t\\n]*$ ]]; then
  echo "Fine. Be that way!"
elif [[  $phrase =~ ^[A-Z]+\?$ ]]; then
  echo "Calm down, I know what I'm doing!"
elif [[ $phrase =~ \?$ ]]; then
  echo "Sure."
elif [[ $phrase =~ ^[A-Z]+$ ]]; then
  echo "Whoa, chill out!"
else
  echo "Whatever."
fi