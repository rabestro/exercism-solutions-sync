#!/usr/bin/env bash

awk 'NF < 2 {print "no arguments";exit 1}
/[^-.[:space:][:digit:]]/{print "invalid arguments";exit 1}
{$0 = $1 * $1 + $2 * $2}
$0 > 100 {print 0; next}
$0 > 25 {print 1; next}
$0 > 1 {print 5; next}
{print 10}' <<< "$@"
