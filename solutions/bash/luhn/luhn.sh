#!/usr/bin/env bash

declare -r number=${1//[[:blank:]]/}

if ! [[ $number =~ ^[[:digit:]]{2,}$ ]]; then
  echo false
  exit 0
fi

declare -i sum=0
declare -i len=${#number}
declare -i isEven=$((len % 2))
declare -i digit

for ((i = 0; i < len; i++)); do
  digit=${number:i:1}
  if ((isEven == 0)); then
    ((digit *= 2))
    if ((digit > 9)); then
      ((digit -= 9))
    fi
  fi
  ((sum+=digit))
  ((isEven=1-isEven))
done

if ((sum % 10 == 0)); then
  echo true
else
  echo false
fi
