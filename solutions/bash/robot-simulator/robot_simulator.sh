#!/usr/bin/env bash

die () { echo "$1" >&2; exit 1; }

main () {
    local -i x=$1 y=$2
    local direction=${3:-north}
    local movements=$4

    [[ $direction =~ north|east|south|west ]] || die "invalid direction: $direction"
    [[ $movements =~ ^[ARL]*$ ]] || die "invalid instruction: $movements"

    for (( i=0; i<${#movements}; i++ )); do
        case ${movements:$i:1} in
            'A') case $direction in
                    north) ((y++)) ;;
                    east) ((x++)) ;;
                    south) ((y--)) ;;
                    west) ((x--)) ;;
                  esac ;;
            'R') case $direction in
                    north) direction=east ;;
                    east) direction=south ;;
                    south) direction=west ;;
                    west) direction=north ;;
                  esac ;;
            'L') case $direction in
                    north) direction=west ;;
                    east) direction=north ;;
                    south) direction=east ;;
                    west) direction=south ;;
                  esac ;;
        esac
    done

    echo $x $y $direction
}

main "$@"
