#!/usr/bin/env bash
sed -Enf parentheses.sed <<< "$@"
