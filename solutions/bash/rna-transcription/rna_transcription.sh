#!/usr/bin/env bash

die() {
  echo >&2 "$@"
  exit 1
}

[[ $1 =~ ^[GCTA]*$ ]] || die "Invalid nucleotide detected."

tr "GCTA" "CGAU" <<<"$1"
