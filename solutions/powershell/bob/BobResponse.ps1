Function Get-BobResponse() {
    <#
    .SYNOPSIS
    Bob is a lackadaisical teenager. In conversation, his responses are very limited.
    
    .DESCRIPTION
    Bob is a lackadaisical teenager. In conversation, his responses are very limited.

    Bob answers 'Sure.' if you ask him a question.

    He answers 'Whoa, chill out!' if you yell at him.

    He answers 'Calm down, I know what I'm doing!' if you yell a question at him.

    He says 'Fine. Be that way!' if you address him without actually saying
    anything.

    He answers 'Whatever.' to anything else.
    
    .PARAMETER HeyBob
    The sentence you say to Bob.
    
    .EXAMPLE
    Get-BobResponse -HeyBob "Hi Bob"
    #>
    [CmdletBinding()]
    Param(
        [string]$HeyBob
    )

    if ($HeyBob -eq $null -or $HeyBob.Trim().Length -eq 0) {
        return "Fine. Be that way!"
    }

    $sentence = $HeyBob.Trim()
    $isYell = $sentence -match "[A-Z]" -and $sentence -eq $sentence.ToUpper()
    $isQuestion = $sentence.EndsWith("?")

    if ($isYell -and $isQuestion) {
        return  "Calm down, I know what I'm doing!"
    }
    if ($isYell) {
        return "Whoa, chill out!"
    }
    if ($isQuestion) {
        return "Sure."
    }
    return "Whatever."
}
