#!/usr/bin/env bash

main () {
  local -ir number=$1
  local -i aliquot_sum=1 opposite factor
  (( number < 1 )) && echo "Classification is only possible for natural numbers." && exit 1

  for (( factor=2; factor*factor<=number; factor++ )); do
    (( number % factor )) && continue
    (( aliquot_sum += factor ))
    (( opposite = number / factor ))
    (( factor != opposite )) && (( aliquot_sum += opposite ))
  done

  if (( number == 1 || aliquot_sum < number )); then
    echo "deficient"
  elif (( aliquot_sum > number )); then
    echo "abundant"
  else
    echo "perfect"
  fi
}

main "$@"
