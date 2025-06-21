#!/usr/bin/env bash

readonly handshake=("wink" "double blink" "close your eyes" "jump")
readonly code=$1

((code >> 4)) && range=({3..0}) || range=({0..3})

for i in "${range[@]}"; do
  if ((1 & (code >> i))); then
    [[ -n $result ]] && result+=","
    result+="${handshake[$i]}"
  fi
done

echo "$result"
