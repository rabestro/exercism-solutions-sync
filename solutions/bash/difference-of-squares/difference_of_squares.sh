#!/usr/bin/env bash

main() {
  local -lr function=$1
  local -i number=$2

  local sum_of_squares=$((number * (number + 1) * (2 * number + 1) / 6))
  local square_of_sum=$(((number * (number + 1) / 2) ** 2))
  local difference=$((square_of_sum - sum_of_squares))

  echo "${!function}"
}

main "$@"
