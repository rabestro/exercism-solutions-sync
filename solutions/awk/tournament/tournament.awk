BEGIN {
    FS = ";"
    PROCINFO["sorted_in"] = "compare"
}
NF == 3 {
    Teams[$1]["MP"]++
    Teams[$2]["MP"]++
    switch ($3) {
        case "win":
            Teams[$1]["W"]++
            Teams[$2]["L"]++
            Teams[$1]["P"]+=3
            break
        case "loss":
            Teams[$2]["W"]++
            Teams[$1]["L"]++
            Teams[$2]["P"]+=3
            break
        default:
            Teams[$1]["D"]++
            Teams[$2]["D"]++
            Teams[$1]["P"]++
            Teams[$2]["P"]++
    }
}
END {
    print "Team                           | MP |  W |  D |  L |  P"
    for (team in Teams)
        printf("%-30s | %2d | %2d | %2d | %2d | %2d\n", team,\
            Teams[team]["MP"],\
            Teams[team]["W"],\
            Teams[team]["D"],\
            Teams[team]["L"],\
            Teams[team]["P"])

}

function compare(team1, one, team2, two) {
    if (one["P"] == two["P"]) return team1 > team2
    return one["P"] < two["P"] ? 1 : -1
}
