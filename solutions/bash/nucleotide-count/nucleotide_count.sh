#!/usr/bin/env bash

main() {
  local -A nucleotides=([A]=0 [C]=0 [G]=0 [T]=0)
  local -r dna="$1"
  local -i i=${#dna}
  local key

  while ((i--)); do
    key="${dna:i:1}"
    [[ -v nucleotides[$key] ]] || { echo "Invalid nucleotide in strand"; exit 1; }
    ((nucleotides[${dna:i:1}]++))
  done

  for key in A C G T; do
    printf '%s: %d\n' "$key" "${nucleotides[$key]}"
  done
}

main "$@"
