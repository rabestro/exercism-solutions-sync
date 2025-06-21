#!/usr/bin/env bash

readonly handshake=("wink" "double blink" "close your eyes" "jump")
readonly code=$1

((code >> 4)) && range="3 -1 0" || range="0 3"

for i in $(eval "seq ${range}"); do
  ((1 & (code >> i))) && {
    [[ -n $result ]] && result+=","
    result+="${handshake[$i]}"
  }
done

echo "$result"
