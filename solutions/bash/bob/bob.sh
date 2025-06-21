#!/usr/bin/env bash

readonly phrase=${1//[!a-zA-Z?]/}

if [[ $1 =~ ^[[:space:]]*$ ]]; then
  echo "Fine. Be that way!"
elif [[ $phrase =~ ^[[:upper:]]+\?$ ]]; then
  echo "Calm down, I know what I'm doing!"
elif [[ $phrase =~ ^[[:upper:]]+$ ]]; then
  echo "Whoa, chill out!"
elif [[ $phrase =~ \?$ ]]; then
  echo "Sure."
else
  echo "Whatever."
fi
