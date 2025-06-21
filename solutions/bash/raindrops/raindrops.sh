#!/usr/bin/env bash

sound=""

if [[ $1%3 -eq 0 ]]; then
  sound=Pling
fi

if [[ $1%5 -eq 0 ]]; then
  sound=${sound}Plang
fi

if [[ $1%7 -eq 0 ]]; then
  sound=${sound}Plong
fi

if [[ -z $sound ]]; then
  sound=$1
fi

echo "$sound"
