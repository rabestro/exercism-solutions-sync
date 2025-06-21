#!/usr/bin/env bash

die() {
  echo "$1"
  exit 1
}

main() {
  local option
  local -i white_queen_row white_queen_col black_queen_row black_queen_col

  while getopts w:b: option
  do
    case "$option" in
    w) IFS=, read -r white_queen_row white_queen_col <<< "$OPTARG" ;;
    b) IFS=, read -r black_queen_row black_queen_col <<< "$OPTARG" ;;
    *)
      echo invalid option
      exit 1
      ;;
    esac
  done

  (( white_queen_row < 0 || black_queen_row < 0 )) && die "row not positive"
  (( white_queen_row > 7 || black_queen_row > 7 )) && die "row not on board"
  (( white_queen_col < 0 || black_queen_col < 0 )) && die "column not positive"
  (( white_queen_col > 7 || black_queen_col > 7 )) && die "column not on board"
  (( white_queen_row == black_queen_row && white_queen_col == black_queen_col )) && die "same position"

  local -ir diagonal_a=$(( white_queen_row - black_queen_row ))
  local -ir diagonal_b=$(( white_queen_col - black_queen_col ))

  (( white_queen_row == black_queen_row || white_queen_col == black_queen_col \
      || ${diagonal_a#-} == ${diagonal_b#-} )) && echo true || echo false
}

main "$@"
