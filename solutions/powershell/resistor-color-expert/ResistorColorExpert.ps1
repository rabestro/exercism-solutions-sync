Function Get-ResistorLabel() {
    <#
    .SYNOPSIS
    Implement a function to get the label of a resistor from its color-coded bands.

    .DESCRIPTION
    Given an array of 1, 4 or 5 colors from a resistor, decode their resistance values and return a string represent the resistor's label.

    .PARAMETER Colors
    The array represent the colors from left to right.

    .EXAMPLE
    Get-ResistorLabel -Colors @("red", "black", "green", "red")
    Return: "2 megaohms ±2%"

    Get-ResistorLabel -Colors @("blue", "blue", "blue", "blue", "blue")
    Return: "666 megaohms ±0.25%"
     #>
    [CmdletBinding()]
    Param(
        [string[]]$Colors
    )

    $colorMap = @{
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
    $toleranceMap = @{
        'grey'   = '0.05%'
        'violet' = '0.1%'
        'blue'   = '0.25%'
        'green'  = '0.5%'
        'brown'  = '1%'
        'red'    = '2%'
        'gold'   = '5%'
        'silver' = '10%'
    }

    if ($Colors.Length -eq 1) {
        return "$( $colorMap[$Colors[0]] ) ohms"
    }

    $resistance = $colorMap[$Colors[0]] * 10 + $colorMap[$Colors[1]]

    if ($Colors.Length -eq 5) {
        $resistance *= 10
        $resistance += $colorMap[$Colors[2]]
    }
    $resistance *= [Math]::Pow(10, $colorMap[$Colors[-2]])

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

    "$($resistance) $($resistanceUnit) ±$($toleranceMap[$Colors[-1]])"
}
