#!/usr/bin/env bash

(($# == 0)) && exit 1

declare -i ItemsCount=0
declare -i Capacity=$1
declare -a Weight
declare -a Value

maxValue() {
  local -i capacity=$1
  local -i i=${2:-0}
  local -i weight
  local -i value
  local -i max=0
  local -i nextItem

  while ((i < ItemsCount))
  do
    ((weight = capacity - Weight[i]))
    if ((weight < 0)); then
      value=0
    else
      ((nextItem = i + 1))
      value=$(maxValue $weight $nextItem)
      ((value += Value[i]))
    fi
    if ((value > max)); then
      max=value
    fi
    ((i++))
  done
  echo $max
}

shift
while (($# > 0)); do
  ((ItemsCount++))
  Weight+=("${1%:*}")
  Value+=("${1#*:}")
  shift
done

maxValue $Capacity
