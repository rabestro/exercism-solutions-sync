#!/usr/bin/env bash

factors () {
    local -i number="$1"
    local -i factor=2
    local -a factors

    while (( factor <= number )); do
        if (( number % factor )); then
            (( ++factor ))
        else
            factors+=( "$factor" )
            (( number /= factor ))
        fi
    done
    echo "${factors[@]}"
}

factors "$1"
