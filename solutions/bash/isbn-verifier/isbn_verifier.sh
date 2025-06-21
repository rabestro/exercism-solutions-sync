#!/usr/bin/env bash

function check_isbn {
  local isbn=${1//-/}
  [[ $isbn =~ ^[[:digit:]]{9}[[:digit:]X]$ ]] || return 1

  local -i i=10 sum=0
  while ((i > 1)); do
    ((sum += ${isbn: -i:1} * i--))
  done

  local check=${isbn: -1}
  ((sum += ${check/X/10}))
  ((sum % 11)) && return 1
  return 0
}

check_isbn "$@" && echo true || echo false
