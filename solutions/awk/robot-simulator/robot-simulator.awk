# These variables are initialized on the command line (using '-v'):
# - x
# - y
# - dir

BEGIN {
    x = +x; y = +y;
    dir = dir ? dir : "north"
}
/R/ {
    dir = dir == "north" ? "east" :
    dir == "east" ? "south" :
    dir == "south" ? "west" :
    "north"
}
/L/ {
    dir = dir == "north" ? "west" :
    dir == "west" ? "south" :
    dir == "south" ? "east" :
    "north"
}
/A/ {
    switch (dir) {
        case "north": y++; break
        case "east": x++; break
        case "south": y--; break
        case "west": x--; break
    }
}
END {
    print x, y, dir
}
