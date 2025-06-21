#!/usr/bin/env bash

modifier () {
    echo $(( $1 / 2 - 5 ))
}

dice () {
    echo $(( RANDOM % 6 + 1 ))
}

generate_ability () {
    local -i round roll
    local -i sum=0 min=6

    for round in {1..4}
    do
        (( sum += roll = $(dice) ))
        (( min = roll < min ? roll : min ))
    done
    echo "$(( sum - min ))"
}

generate () {
    local -ir constitution="$(generate_ability)"
    local -ir hitpoints=$(( 10 + $(modifier "$constitution") ))

    echo "constitution $constitution"
    echo "hitpoints $hitpoints"

    for ability in strength dexterity intelligence wisdom charisma
    do
        printf "%s %d\n" "$ability" "$(generate_ability)"
    done
}

main () {
    case "$1" in
        modifier) modifier "$2";;
        generate) generate;;
    esac
}

main "$@"
