#!/usr/bin/env bash

BC_LINE_LENGTH=1000 bc sieve.bc <<< "$1" | xargs
