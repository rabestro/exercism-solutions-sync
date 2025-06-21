#!/usr/bin/env bash

factor "$1" | sed -E 's/[0-9]+: ?//'
