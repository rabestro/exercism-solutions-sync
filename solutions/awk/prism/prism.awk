# The first line of input is the starting x, y and angle.
# Any remaining input is the prisms: ID, x, y, and refraction angle.
# The program is expected to print out prism IDs, space separated on one line.

BEGIN {
    PI = atan2(0, -1)
    DR = PI / 180
}
NR == 1 {
    Current_X = $1
    Current_Y = $2
    Current_A = $3
}
NR > 1 {
    id = $1
    X[id] = $2
    Y[id] = $3
    A[id] = $4
}
END {
    for (NF = 0; prism = next_prism();)
        $(++NF) = prism
    print
}
function abs(x) {
    return x > 0 ? x : -x
}
function next_prism(   i, dx, dy, id, min, distance) {
    for (i in X) {
        if (X[i] == Current_X && Y[i] == Current_Y)
            continue

        dx = X[i] - Current_X
        dy = Y[i] - Current_Y
        distance = sqrt(dx * dx + dy * dy)
        dx = abs(cos(Current_A * DR) * distance - dx)
        dy = abs(sin(Current_A * DR) * distance - dy)

        if (dx >= 0.1 || dy >= 0.1) continue
        if (min && min < distance) continue
        id = i
        min = distance
    }
    Current_A += A[id]
    Current_X = X[id]
    Current_Y = Y[id]
    return id
}
