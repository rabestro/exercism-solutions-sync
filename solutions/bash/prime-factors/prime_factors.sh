#!/usr/bin/env bash

main () {
    local -i number="$1"
    local -i factor=2

    while (( factor <= number ))
    do
        if (( number % factor ))
        then
            (( ++factor ))
        else
            printf "%d " "$factor"
            (( number /= factor ))
        fi
    done
}

main "$1" | sed 's/ $/\n/'
