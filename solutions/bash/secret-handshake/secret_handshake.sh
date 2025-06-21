#!/usr/bin/env bash

readonly handshake=("wink" "double blink" "close your eyes" "jump")
readonly code=$1

function join_by() {
  local IFS="$1"
  shift
  echo "$*"
}

((code >> 4)) && range="3 -1 0" || range="0 3"

for i in $(eval "seq ${range}"); do
  ((1 & (code >> i))) && result+=("${handshake[$i]}")
done

join_by , "${result[@]}"
