#!/usr/bin/env bash

chr() {
  printf \\$(printf '%03o' $1)
}

ord() {
  LC_CTYPE=C printf '%d' "'$1"
}

main () {
    local -ir a=$(ord A)
    local -ir half=$(( $(ord $1) - a + 1 ))
    local -ir size=$(( 2 * half - 1 ))
    local -a board
    local -i row symbol i l r

    for (( row = 0; row < size; ++row ))
    do
        for (( i = 1; i <= size; ++i ))
        do
            board[$i]=' '
        done

        if (( row < half ))
        then
            (( symbol = a + row ))
            (( l = half - row ))
            (( r = half + row ))
        else
            (( symbol = a + size - row - 1 ))
            (( l = half + row - size + 1 ))
            (( r = half - row + size - 1 ))
        fi
        local letter=$(chr "$symbol")
        board[$l]=$letter
        board[$r]=$letter
        printf '%s' "${board[@]}"
        echo
    done
}

main "$@"
