#!/usr/bin/env bash

if [[ -t 0 ]]
then
    echo ""
    exit 0
fi

gawk --exec ocr_numbers.awk
