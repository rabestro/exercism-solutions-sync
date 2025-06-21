#!/usr/bin/env bash

die () { echo "$1"; exit 1; }

main () {
  (( $# != 2 )) && die "Invalid arg count"
  for i; do [[ $i = *[^[:digit:].-]* ]] && die "Non-numeric arg"; done

  bc <<< "d=$1^2 + $2^2; if (d > 100) 0 else if (d > 25) 1 else if (d > 1) 5 else 10"
}

main "$@"
