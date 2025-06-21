#!/usr/bin/env bash
die() {
  echo "$1"
  exit 1
}

canAttack() {
  local a="$1"
  local b="$2"
  local c="$3"
  local d="$4"
  local e=$((a - c))
  local f=$((b - d))

  if ((a == c || b == d || "${e/#-}" == "${f/#-}")); then
    echo "true"
  else
    echo "false"
  fi
}

main() {
  local row1
  local col1
  local row2
  local col2

  IFS=',' read -r row1 col1 row2 col2 <<<"$2,$4"

  ((row1 < 0 || row2 < 0)) && die "row not positive"
  ((row1 > 7)) && die "row not on board"
  ((col1 < 0)) && die "column not positive"
  ((col1 > 7)) && die "column not on board"
  ((row1 == row2 && col1 == col2)) && die "same position"
  canAttack row1 col1 row2 col2
}

main "$@"
