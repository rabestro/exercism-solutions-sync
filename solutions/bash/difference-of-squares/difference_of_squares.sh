#!/usr/bin/env bash

readonly function=$1
readonly number=$2

readonly sum_of_squares=$((number * (number + 1) * (2 * number + 1) / 6))
readonly square_of_sum=$(((number * (number + 1) / 2) ** 2))
readonly difference=$((square_of_sum - sum_of_squares))

echo "${!function}"
