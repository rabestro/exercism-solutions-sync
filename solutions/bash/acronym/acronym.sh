#!/usr/bin/env bash

sed "s/\([[:alpha:]]\)[a-z'A-Z]*[^a-zA-Z]*/\1/g" <<< $1 | tr "[:lower:]" "[:upper:]"
