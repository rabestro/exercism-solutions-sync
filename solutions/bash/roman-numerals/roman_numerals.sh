#!/usr/bin/env bash

function arabic_to_roman_numerals() {
    local -i number=$1
    local -A Roman=(
        [M]=1000
        [D]=500
        [C]=100
        [L]=50
        [X]=10
        [V]=5
        [I]=1
        [CM]=900
        [CD]=400
        [XC]=90
        [XL]=40
        [IX]=9
        [IV]=4
    )

    local result=""
    for key in M CM D CD C XC L XL X IX V IV I; do
        local -i value="${Roman[$key]}"
        while (( number >= value )); do
            result+="$key"
            (( number -= value ))
        done
    done
    echo "$result"
}

arabic_to_roman_numerals "$1"
