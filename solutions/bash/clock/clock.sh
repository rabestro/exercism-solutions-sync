#!/usr/bin/env bash

die () { echo "$1"; exit 1; }

print_clock () {
    local -ir MINUTES_IN_DAY=60*24
    local -ir delta="$3$4"
    local -ir total_minutes=$(( MINUTES_IN_DAY + $1 % 24 * 60 + $2 % MINUTES_IN_DAY + delta % MINUTES_IN_DAY ))
    local -ir hours='total_minutes / 60 % 24'
    local -ir minutes='total_minutes % 60'
    printf "%02d:%02d\n" $hours $minutes
}

validate_parameters () {
    [ $# -ne 2 ] && [ $# -ne 4 ] && die "invalid arguments"
    [[ $1 =~ ^-?[0-9]+$ ]] || die "Hours must be an integer"
    [[ $2 =~ ^-?[0-9]+$ ]] || die "Minutes must be an integer"
    [[ $3 =~ ^[+-]$ ]] || [ -z "$3" ] || die "invalid arguments"
    [[ $4 =~ ^[0-9]+$ ]] || [ -z "$4" ] || die "Delta must be an integer"
}

validate_parameters "$@"

print_clock "$@"
