Function Get-Slices() {
    <#
    .SYNOPSIS
    Given a string of digits, output all the contiguous substrings of length `n` in that string.
    
    .DESCRIPTION
    The function takes a string of digits and returns all the contiguous substrings of length `n` in that string.

    .PARAMETER Series
    The string of digits

    .PARAMETER SliceLength
    The length of the slices to return
    
    .EXAMPLE
    Get-Slices -Series "01234" -SliceLength 2
    
    Returns: @("01", "12", "23", "34")
    #>
    [CmdletBinding()]
    Param(
        [string]$Series,
        [int]$SliceLength
    )

    if ($Series.Length -eq 0) { Throw "Series cannot be empty." }
    if ($SliceLength -eq 0) { Throw "Slice length cannot be zero." }
    if ($SliceLength -lt 0 ) { Throw "Slice length cannot be negative." }
    if ($SliceLength -gt $Series.Length ) { Throw "Slice length cannot be greater than series length." }  
    @(
        for ($i = 0; $i -le $Series.Length - $SliceLength; $i++) {
            $Series.Substring($i, $SliceLength)
        }
    )
}
