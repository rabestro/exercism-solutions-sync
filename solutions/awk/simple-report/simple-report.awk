BEGIN {
    FS = ","
}
{
    sum = ($3$4) + ($5$6)
    average = sum / 2
    print "#" $1 ", " $2 " = " average
}
