# These variables are initialized on the command line (using '-v'):
# - name

BEGIN {
    ChildrensInitials = "ABCDEFGHIJKL"
    Initial = substr(name, 1, 1)
    Position = index(ChildrensInitials, Initial)

    Plants["G"] = "grass"
    Plants["R"] = "radishes"
    Plants["C"] = "clover"
    Plants["V"] = "violets"

    ORS = ""
    OFS = " "
    while (i++ < length(ChildrensInitials) * 2) FIELDWIDTHS = FIELDWIDTHS" 1"
}
NR == 2 {
    print " "
}
{
    print Plants[$(Position * 2 - 1)], Plants[$(Position * 2)]
}
