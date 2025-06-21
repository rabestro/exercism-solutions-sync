#!/usr/bin/env bash

declare -ar THROWS=("$@")
declare -ir MAX_PINS=10
declare -ir LAST_FRAME=10
declare -A ERROR_MESSAGE=(
    [incomplete]="Score cannot be taken until the end of the game"
    [negative]="Negative roll is invalid"
    [exceeds]="Pin count exceeds pins on the lane"
    [game_over]="Cannot roll after game is over"
)

die () {
    echo "${ERROR_MESSAGE[$1]:-$1}" >&2; exit 1
}

validate_pins () {
    local -ir pins=$1
    (( pins < 0 )) && die negative
    (( pins > MAX_PINS )) && die exceeds
}

get_pins () {
    local -ir throw=$1
    (( throw >= ${#THROWS[*]} )) && die incomplete

    local -ir pins=${THROWS[$throw]}
    validate_pins $pins
    echo $pins
}

score () {
    local -i throw=0 score=0 frame=0 first_throw second_throw third_throw spare strike

    while (( throw < ${#THROWS[*]} && frame < LAST_FRAME ))
    do
        (( frame++ ))
        first_throw=$(get_pins $throw)
        score+=first_throw
        (( throw++ ))

        second_throw=$(get_pins $throw)
        (( first_throw < MAX_PINS )) && validate_pins $(( first_throw + second_throw ))
        score+=second_throw
        (( throw++ ))

        strike='first_throw == MAX_PINS'
        spare='!strike && first_throw + second_throw == MAX_PINS'

        if (( strike || spare )); then
            third_throw=$(get_pins $throw)
            (( $? )) && exit 1
            if (( strike && frame == LAST_FRAME && second_throw < MAX_PINS )); then
                validate_pins $(( second_throw + third_throw ))
            fi
            score+=third_throw
        fi
        (( strike )) && (( throw-- ))
    done
    (( frame == 10 )) || die incomplete
    (( throw += spare + 2*strike ))
    (( throw == ${#THROWS[*]} )) || die game_over
    echo $score
}

score "$@"
