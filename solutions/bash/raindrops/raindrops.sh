#!/usr/bin/env bash

number=$1

if (( number % 3 == 0 )); then
  sound=Pling
fi

if (( number % 5 == 0 )); then
  sound+=Plang
fi

if (( number % 7 == 0 )); then
  sound+=Plong
fi

echo ${sound:-$number}
