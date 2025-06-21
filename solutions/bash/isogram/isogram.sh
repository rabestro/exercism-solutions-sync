#!/usr/bin/env bash

main () {
   local phrase=${1@L}
   grep -qvE '([a-z]).*\1' <<< "$phrase" && echo true || echo false
}

main "$@"
