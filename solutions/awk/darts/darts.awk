{
    print score($1 * $1 + $2 * $2)
}

function score(distance) {
    if (distance > 100) return 0
    if (distance > 25) return 1
    if (distance > 1) return 5
    return 10
}
