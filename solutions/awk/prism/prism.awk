# The first line of input is the starting x, y and angle.
# Any remaining input is the prisms: ID, x, y, and refraction angle.
# The program is expected to print out prism IDs, space separated on one line.

BEGIN {
    PI = atan2(0, -1)
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
    for (NF = 0; prism = next_prism();) {
        $(++NF) = prism
        if (NF > 50) break
    }
    print
}

function is_not_equals(a, b) {
    return (a < b ? b - a : a - b) > 0.01
}
function normalise_angle(angle) {
    angle = sprintf("%.2f", angle)
    if (angle >= 180) angle -= 360
    if (angle < -180) angle += 360
    return angle
}
function next_prism(   i, dx, dy, angle, id, min, distance) {
    #print "Current:", Current_X, Current_Y, Current_A
    for (i in X) {
        dx = X[i] - Current_X
        dy = Y[i] - Current_Y
        #printf ".....%02d ", i
        if (!dx && !dy) continue
        angle = normalise_angle(atan2(dy, dx) * 180 / PI)
        #if (angle >= 180) angle -= 360
        #print "a =", angle
        if (is_not_equals(angle, Current_A)) continue
        distance = sqrt(dx * dx + dy * dy)
        if (min && min < distance) continue
        id = i
        min = distance
        #print "min ->", id, distance
    }
    Current_A = normalise_angle(Current_A + A[id])

    # Current_A = sprintf("%.3f", Current_A)
    #if (Current_A >= 180) Current_A -= 360
    #if (Current_A < -180) Current_A += 360
    Current_X = X[id]
    Current_Y = Y[id]
    #print "=>", id, Current_X, Current_Y, Current_A
    return id
}
