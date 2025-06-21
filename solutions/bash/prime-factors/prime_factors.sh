#!/usr/bin/env bash

bc factors.bc <<< "$1" | xargs
