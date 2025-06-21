#!/usr/bin/env bash

main () {
    local -r phrase=${1@L}
    local symbols=${phrase//[^[:alpha:]]/}

    for letter in {a..z}
    do
       symbols="${symbols/$letter/}"
    done

    [[ -z $symbols ]] && echo 'true' || echo 'false'
}

main "$@"
