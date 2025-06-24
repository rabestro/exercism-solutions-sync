Function Test-MatchingBrackets() {
    <#
    .SYNOPSIS
    Determine if all brackets inside a string paired and nested correctly.
    
    .DESCRIPTION
    Given a string containing brackets `[]`, braces `{}`, parentheses `()`, or any combination thereof, verify that any and all pairs are matched and nested correctly.
    The string may also contain other characters, which for the purposes of this exercise should be ignored.
    
    .PARAMETER Text
    The string to be verified.
    
    .EXAMPLE
    Test-MatchingBrackets("[]") => True
    Test-MatchingBrackets("[)]") => False
    #>
    [CmdletBinding()]
    Param(
        [string]$Text
    )

    $brackets = @{
        '[' = ']'
        '{' = '}'
        '(' = ')'
    }
    $chars = ($Text -replace '[^[\](){}]', '').ToCharArray()
    $stack = New-Object System.Collections.Stack
    
    foreach ($char in $chars) {
        $current = $char.ToString()
        if ($brackets.ContainsKey($current)) {
            $stack.Push($current)
            continue
        } 
        if ($stack.Count -eq 0) {
            return $false
        }
        $last = $stack.Pop()
        if ($brackets[$last] -ne $current) {
            return $false
        }
    }
    return $stack.Count -eq 0
}
