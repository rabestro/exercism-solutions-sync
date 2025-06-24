Function Get-ResistorLabel() {
    <#
    .SYNOPSIS
    Implement a function to get the label of a resistor with three color-coded bands.

    .DESCRIPTION
    Given an array of colors from a resistor, decode their resistance values and return a string represent the resistor's label.

    .PARAMETER Colors
    The array repesent the 3 colors from left to right.

    .EXAMPLE
    Get-ResistorLabel -Colors @("red", "white", "blue")
    Return: "29 megaohms"
     #>
    [CmdletBinding()]
    Param(
        [string[]]$Colors
    )
    $colorMap = [ordered]@{
        'black'  = 0
        'brown'  = 1
        'red'    = 2
        'orange' = 3
        'yellow' = 4
        'green'  = 5
        'blue'   = 6
        'violet' = 7
        'grey'   = 8
        'white'  = 9
    }
    $resistance = $colorMap[$Colors[0]] * 10 + $colorMap[$Colors[1]]
    $resistance *= [Math]::Pow(10, $colorMap[$Colors[2]])
    $resistanceUnit = "ohms"

    if ($resistance -ge 1000) {
        $resistance = $resistance / 1000
        $resistanceUnit = "kiloohms"
    }

    if ($resistance -ge 1000) {
        $resistance = $resistance / 1000
        $resistanceUnit = "megaohms"
    }

    if ($resistance -ge 1000) {
        $resistance = $resistance / 1000
        $resistanceUnit = "gigaohms"
    }

    return "$resistance $resistanceUnit"
}