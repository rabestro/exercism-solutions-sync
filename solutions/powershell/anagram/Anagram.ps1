Function Invoke-Anagram() {
    <#
    .SYNOPSIS
    Determine if a word is an anagram of other words in a list.

    .DESCRIPTION
    An anagram is a word formed by rearranging the letters of another word, e.g., spar, formed from rasp.
    Given a word and a list of possible anagrams, find the anagrams in the list.

    .PARAMETER Subject
    The word to check

    .PARAMETER Candidates
    The list of possible anagrams

    .EXAMPLE
    Invoke-Anagram -Subject "listen" -Candidates @("enlists" "google" "inlets" "banana")
    #>
    [CmdletBinding()]
    Param(
        [string]$Subject,
        [string[]]$Candidates
    )

    $word = $Subject.ToLower().ToCharArray() | Sort-Object 
    
    # $Candidates | Where-Object {
    #     $candidate = $_.ToLower().ToCharArray() | Sort-Object
    #     $candidate -eq $word -and $_ -ne $Subject
    # }

    $Candidates | 
    Where-Object { $_ -ne $Subject -and $_.Length -eq $Subject.Length } |
    Where-Object { ($_.ToLower().ToCharArray() | Sort-Object) -eq $word }
}
