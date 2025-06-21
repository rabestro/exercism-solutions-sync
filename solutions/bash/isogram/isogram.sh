#!/usr/bin/env bash

main () {
   local phrase=${1@L}

   if [[ $phrase =~ ([a-z]).*\1 ]]
   then
     echo false
   else
     echo true
   fi
}

main "$@"
