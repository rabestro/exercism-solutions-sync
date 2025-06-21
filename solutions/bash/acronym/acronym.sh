#!/usr/bin/env bash

sed "s/\([[:alpha:]]\)[a-z'A-Z]*[^a-zA-Z]*/\U\1/g" <<< $1
