#!/usr/bin/env bash

OUT=$(bc sieve.bc <<< "$1")
echo ${OUT//$'\\\n'/}
