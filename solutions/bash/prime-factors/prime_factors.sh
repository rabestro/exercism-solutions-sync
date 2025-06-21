#!/usr/bin/env bash

main () {
    local -i number="$1"
    local -i factor=2

    while (( factor <= number ))
    do
        if (( number % factor ))
        then
            (( ++factor ))
            continue
        fi
        printf "%d " "$factor"
        (( number /= factor ))
    done
}

main "$1" | sed 's/ $//'
