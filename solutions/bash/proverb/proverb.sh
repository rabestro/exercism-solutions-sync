#!/usr/bin/env bash


main () {
    local -ar words=( "$@" )

    for (( i = 0; i < ${#words[@]} - 1; ++i ))
    do
        echo "For want of a ${words[$i]} the ${words[(( $i + 1 ))]} was lost."
    done

    (( $# > 0 )) && echo "And all for the want of a $1."
}

(( $# == 0 )) && echo "" && exit
main "$@"
