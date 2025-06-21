#!/usr/bin/env bash

function join_by() {
  local IFS="$1"
  shift
  echo "$*"
}

handshake=("wink" "double blink" "close your eyes" "jump")
code=$1
result=()

if ((code >> 4)); then
  for i in {3..0..-1}; do
    ((1 & (code >> i))) && result+=("${handshake[$i]}")
  done
else
  for i in {0..3}; do
    ((1 & (code >> i))) && result+=("${handshake[$i]}")
  done
fi

join_by , "${result[@]}"
