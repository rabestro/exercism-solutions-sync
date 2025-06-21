#!/usr/bin/env bash

declare -ar subj=(
    house malt rat cat dog
    'cow with the crumpled horn'
    'maiden all forlorn'
    'man all tattered and torn'
    'priest all shaven and shorn'
    'rooster that crowed in the morn'
    'farmer sowing his corn'
    'horse and the hound and the horn'
)
declare -ar verb=(
    'lay in' ate killed worried tossed milked
    kissed married woke kept 'belonged to'
)
declare -ir max_verse=${#subj[@]}

phrase () {
    local -i verse=$1
    echo "the ${subj[$verse]}"
    (( verse-- )) && echo "that ${verb[$verse]} $(phrase $verse)"
}

nursery_rhyme () {
    local -ir verse=$(( $1 - 1 ))
    echo "This is $(phrase $verse) that Jack built."
    echo ''
}

main() {
    local -ir from=$1 to=$2
    local -i verse
    
    if (( from < 1 || from > max_verse || to < from || to > max_verse )); then
        echo "invalid arguments"
        exit 1
    fi

    for ((verse=$from; verse<=$to; verse++))
    do
        nursery_rhyme $verse
    done
}

main "$@"
