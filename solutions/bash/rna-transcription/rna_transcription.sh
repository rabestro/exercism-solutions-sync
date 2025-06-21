#!/usr/bin/env bash

die() {
  echo >&2 "$@"
  exit 1
}

[[ $1 =~ ^[GCTA]*$ ]] && tr "GCTA" "CGAU" <<<"$1" || die "Invalid nucleotide detected."
