#!/usr/bin/env bash

(( $1 < $3 && $2 < $3 )) && echo "invalid goal" && exit 1

declare Source Target
declare -r Goal="$3"
declare -A Volume=( [one]="$1" [two]="$2" )
declare -A Water=( [one]=0 [two]=0 )
declare -a History
declare -i Step=0

is_empty () {
    [[ ${Water[$1]} == 0 ]]
}

is_full () {
    [[ ${Water[$1]} == ${Volume[$1]} ]]
}

is_goal () {
    [[ ${Water[$1]} == $Goal ]]
}

fill () {
    (( Water[$1] = Volume[$1] ))
}

empty () {
    (( Water[$1] = 0 ))
}

pouring () {
    local -ir free_volume=$(( Volume[Target] - Water[Target] ))
    local -ir pouring_volume=$(( Water[Source] < free_volume ? Water[Source] : free_volume ))

    (( Water[Source] -= pouring_volume ))
    (( Water[Target] += pouring_volume ))
}

process_step() {
    if is_empty $Source
    then
        fill $Source
    elif (( Volume[$Target] == Goal ))
    then
        fill $Target
    elif is_full $Target
        empty $Target
    then
        pouring
    fi
}

record_step () {
    local -r state="["${Water[$Source]}","${Water[$Target]}"]"

    if [[ "${History[@]}" =~ "${state}" ]]
    then
        echo "invalid goal ..." "${History[@]}" " State: " "${state}"
        echo $Source $Target
        exit 1
    else
        History+=( "$state" )
    fi
}

main () {
    if [[ $4 == "one" ]]
    then
        Source=one
        Target=two
    else
        Source=two
        Target=one
    fi

    until is_goal Source || is_goal Target
    do
        process_step
        record_step
    done

    local winner second
    if is_goal one
    then
        winner=one
        second=two
    else
        winner=two
        second=one
    fi
    printf "%s %s %s" ${#History} $winner ${Water[$second]}
}

main "$@"
