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
    'lay in' ate killed worried tossed milked kissed married woke kept 'belonged to'
)
declare -ir max_verse=${#subj[@]}

phrase () {
    local -i verse=$1
    echo "the ${subj[$verse]}"
    (( verse-- )) && echo "that ${verb[$verse]} $(phrase $verse)"
}

nursery_rhyme () {
    local -ir verse=$1-1
    echo "This is $(phrase $verse) that Jack built."
    echo ''
}

main() {
    local -i verse=$1 end_verse=$2

    if (( verse < 1 || verse > max_verse || end_verse < verse || end_verse > max_verse ))
    then
        echo "invalid arguments"
        exit 1
    fi

    while (( verse <= end_verse ))
    do
        nursery_rhyme $(( verse++ ))
    done
}

main "$@"
