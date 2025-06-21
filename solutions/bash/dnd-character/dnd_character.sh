#!/usr/bin/env bash

modifier () {
    echo $(( $1 / 2 - 5 ))
}

dice() {
    echo $(( $RANDOM % 6 + 1 ))
}

generate_ability() {
    local -i result=0 roll min=6

    for round in {1..4}
    do
        roll=$(dice)
        result+=roll
        let "min = roll < min ? roll : min"
    done

    echo "$(( result -= min ))"
}

generate() {
    local -ir constitution=$(generate_ability)
    local -ir hitpoints="10 + $(modifier $constitution)"

    printf "constitution %d\n" $constitution
    printf "hitpoints %d\n" $hitpoints

    for ability in strength dexterity intelligence wisdom charisma
    do
        printf "%s %d\n" $ability $(generate_ability)
    done
}

main () {
    if [[ "$1" == "modifier" ]]
    then
        modifier "$2"
    else
        generate
    fi
}

main "$@"
