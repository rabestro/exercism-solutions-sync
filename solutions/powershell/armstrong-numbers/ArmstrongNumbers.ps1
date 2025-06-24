Function Invoke-ArmstrongNumbers() {
    <#
    .SYNOPSIS
    Determine if a number is an Armstrong number.

    .DESCRIPTION
    An Armstrong number is a number that is the sum of its own digits each raised to the power of the number of digits.

    .PARAMETER Number
    The number to check.

    .EXAMPLE
    Invoke-ArmstrongNumbers -Number 12
    #>
    [CmdletBinding()]
    Param(
        [Int64]$Number
    )

    $exponent = $Number.ToString().Length

    $sumOfDigits = $Number.ToString().ToCharArray() | 
    ForEach-Object { [Math]::Pow([int]::Parse($_), $exponent) } |
    Measure-Object -Sum | 
    Select-Object -ExpandProperty Sum

    $sumOfDigits -eq $Number
}
