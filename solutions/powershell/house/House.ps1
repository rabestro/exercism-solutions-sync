Function Get-Verse([int]$verse) {
    if ($verse -eq 1) {
        return 'This is the house that Jack built.'
    }
    $subject = [ordered]@{
        'the malt'                             = 'lay in'
        'the rat'                              = 'ate'
        'the cat'                              = 'killed'
        'the dog'                              = 'worried'
        'the cow with the crumpled horn'       = 'tossed'
        'the maiden all forlorn'               = 'milked'
        'the man all tattered and torn'        = 'kissed'
        'the priest all shaven and shorn'      = 'married'
        'the rooster that crowed in the morn'  = 'woke'
        'the farmer sowing his corn'           = 'kept'
        'the horse and the hound and the horn' = 'belonged to'
    }
    $verse -= 2
    $sentence = $verse..0 | 
    ForEach-Object { $subject.Keys[$_] + ' that ' + $subject.Values[$_] } 
    'This is ' + ($sentence -join ' ') + ' the house that Jack built.'
}

Function Get-Rhyme() {
    <#
    .SYNOPSIS
    Recite the nursery rhyme 'This is the House that Jack Built'.

    .DESCRIPTION
    Given the start verse and the end verse, return a string reciting the rhyme from that range.

    .PARAMETER Start
    The start verse.

    .PARAMETER End
    The end verse.

    .EXAMPLE
    Get-Rhyme -Start 1 -End 2
    Return:
    @"
    This is the house that Jack built.
    This is the malt that lay in the house that Jack built.
    "@
     #>
    [CmdletBinding()]
    Param(
        [int]$Start,
        [int]$End
    )

    ($Start..$End | ForEach-Object { Get-Verse $_ }) -join "`n"
}