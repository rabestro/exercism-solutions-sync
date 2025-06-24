Function Invoke-Panagram() {
    <#
    .SYNOPSIS
    Determine if a sentence is a pangram.
    
    .DESCRIPTION
    A pangram is a sentence using every letter of the alphabet at least once.
    
    .PARAMETER Sentence
    The sentence to check
    
    .EXAMPLE
    Invoke-Panagram -Sentence "The quick brown fox jumps over the lazy dog"
    
    Returns: $true
    #>
    [CmdletBinding()]
    Param(
        [string]$Sentence
    )

    $alphabet = 'a'..'z'
    
    $count = $Sentence.ToLower().ToCharArray() | 
    Where-Object { $_ -in $alphabet } | 
    Select-Object -Unique | 
    Measure-Object | 
    Select-Object -ExpandProperty Count 
    
    $count -eq $alphabet.Length
}
