Function Get-RomanNumerals() {
    <#
    .SYNOPSIS
    Given a number, convert it into a roman numeral.

    .DESCRIPTION
    Convert a positive integer into a string representation of that integer in roman numeral form.
    
    .PARAMETER Number
    The number to turn into roman numeral.

    .EXAMPLE
    Get-RomanNumerals -Number 1
    return: 'I'
    Get-RomanNumerals -Number 3999
    return: 'MMMCMXCIX'
    #>
    [CmdletBinding()]
    Param(
        [int]$Number
    )

    if ($Number -lt 1 -or $Number -gt 3999) {
        Throw "Number has to be positive integer in range of 1-3999."
    }

    $RomanNumerals = @{
        1 = 'I'
        4 = 'IV'
        5 = 'V'
        9 = 'IX'
        10 = 'X'
        40 = 'XL'
        50 = 'L'
        90 = 'XC'
        100 = 'C'
        400 = 'CD'
        500 = 'D'
        900 = 'CM'
        1000 = 'M'
    }
    
    $result = ''
    foreach ($key in $RomanNumerals.Keys | Sort-Object -Descending) {
        #$key
        while ($Number -ge $key) {
            $result += $RomanNumerals[$key]
            $Number -= $key
        }
    }
    return $result
}