#!/usr/bin/env bash

die () { echo "$1" >&2; exit 1; }

validate_parameters () {
    (( $# == 2 || $# == 4 )) || die "invalid arguments"
    [[ $1 =~ ^-?[[:digit:]]+$ ]] || die "Hours must be an integer"
    [[ $2 =~ ^-?[[:digit:]]+$ ]] || die "Minutes must be an integer"
    [[ $3 =~ ^[+-]$ ]] || [ -z "$3" ] || die "invalid arguments"
    [[ $4 =~ ^[[:digit:]]+$ ]] || [ -z "$4" ] || die "Delta must be an integer"
}

print_clock () {
    local -ir MINUTES_IN_DAY=1440
    local -ir delta=$3$4
    local -ir total_minutes=$(( MINUTES_IN_DAY + $1 % 24 * 60 + $2 % MINUTES_IN_DAY + delta % MINUTES_IN_DAY ))
    local -ir hours=$(( total_minutes / 60 % 24 ))
    local -ir minutes=$(( total_minutes % 60 ))
    printf "%02d:%02d\n" $hours $minutes
}

validate_parameters "$@"
print_clock "$@"
