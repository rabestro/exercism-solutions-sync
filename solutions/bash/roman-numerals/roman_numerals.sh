#!/usr/bin/env bash

convert_arabic_to_roman() {
    local -i target_number=$1
    local -A RomanNumerals=(
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

    for numeral in M CM D CD C XC L XL X IX V IV I; do
        while (( target_number >= RomanNumerals[$numeral] )); do
            result+="$numeral"
            (( target_number -= RomanNumerals[$numeral] ))
        done
    done

    echo "$result"
}

convert_arabic_to_roman "$1"
