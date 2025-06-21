#!/usr/bin/env bash

declare -ar THROWS=("$@")
declare -ir MAX_PINS=10
declare -ir LAST_FRAME=10

die () {
    echo "$1" >&2
    exit 1
}

validate_pins () {
    local -ir pins=$1
    (( pins < 0 )) && die "Negative roll is invalid."
    (( pins > MAX_PINS )) && die "Pin count exceeds pins on the lane."
}

get_pins () {
    local -ir throw=$1
    if (( throw >= ${#THROWS[*]} )); then
        die "Score cannot be taken until the end of the game."
    fi

    local -ir pins=${THROWS[$throw]}
    validate_pins $pins
    echo $pins
}

score () {
    local -i throw=0 score=0 frame=0 first_throw second_throw third_throw spare strike

    while (( throw < ${#THROWS[*]} && frame < LAST_FRAME ))
    do
        frame+=1
        first_throw=$(get_pins $throw)
        (( $? )) && exit 1
        score+=first_throw
        (( throw++ ))

        second_throw=$(get_pins $throw)
        (( first_throw < MAX_PINS )) && validate_pins $(( first_throw + second_throw ))
        score+=second_throw
        (( throw++ ))

        strike=$(( first_throw == MAX_PINS ))
        spare=$(( !strike && first_throw + second_throw == MAX_PINS ))

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
    (( frame == 10 )) || die "Score cannot be taken until the end of the game."
    (( throw += spare + 2*strike ))
    (( throw == ${#THROWS[*]} )) || die "Cannot roll after game is over."
    echo $score
}

score "$@"
