#!/usr/bin/env bash

echo "$1" | sed "s/\([[:alpha:]]\)[a-z'A-Z]*[^a-zA-Z]*/\U\1/g"
