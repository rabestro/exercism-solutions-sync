#!/usr/bin/env bash

validate_triangle () {
    ((
        1 == $(bc <<<"$1 <= 0 || $2 <= 0 || $3 <= 0 || $1 > $2 + $3 || $2 > $1 + $3 || $3 > $1 + $2")
    )) && echo "false" && exit
}

is_equilateral () {
    [[ $1 == $2 && $2 == $3 ]] && echo "true" || echo "false"
}

is_isosceles () {
    [[ $1 == $2 || $2 == $3 || $1 == $3 ]] && echo "true" || echo "false"
}

is_scalene () {
    [[ $1 != $2 && $1 != $3 && $2 != $3 ]] && echo "true" || echo "false"
}

main () {
    local -r triangle="$1"
    shift
    validate_triangle "$@"
    case "$triangle" in
        equilateral) is_equilateral "$@" ;;
        isosceles) is_isosceles "$@" ;;
        scalene) is_scalene "$@" ;;
    esac
}

main "$@"
