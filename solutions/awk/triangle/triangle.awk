# These variables are initialized on the command line (using '-v'):
# - type

{
    print @type() ? "true" : "false"
}

function equilateral() {
    return $1 && $1 == $2 && $2 == $3
}

function isosceles() {
    return $1 == $2 && $1 + $2 > $3 \
        || $1 == $3 && $1 + $3 > $2 \
        || $2 == $3 && $2 + $3 > $1
}

function scalene() {
    return $1 != $2 && $1 != $3 && $2 != $3 \
        && $1 < $2 + $3 && $2 < $3 + $1 && $3 < $1 + $2
}
