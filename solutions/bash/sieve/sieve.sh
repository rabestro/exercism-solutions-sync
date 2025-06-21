#!/usr/bin/env bash

primes=$(bc sieve.bc <<< "$1")
echo ${primes//$'\\\n'/}
