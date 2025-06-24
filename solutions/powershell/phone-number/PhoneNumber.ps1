Function Get-PhoneNumber() {
    <#
    .SYNOPSIS
    Clean up user-entered phone numbers so that they can be sent SMS messages.

    .DESCRIPTION
    Given a phone number string, check if it's a valid phone number that complied with the NANP system.
    Return the cleaned number string if it's valid, otherwise throw the relevant error.
    Also provide user the option to print out the number in pretty format.

    .PARAMETER Number
    The phone number string to be processed.

    .PARAMETER Pretty
    Provide optional flag that will print out the phone number in pretty format: (Area)-Exchange-Number
    
    .EXAMPLE
    Get-PhoneNumber -Number '+1 (223) 456-7890'
    return: '2234567890'

    Get-PhoneNumber -Number '555.888.9999' -Pretty
    return: '(555)-888-9999'
    #>
    [CmdletBinding()]
    Param(
        [string]$Number,
        [switch]$Pretty
    )

    if ($Number -match '[a-zA-Z]') {
        throw 'Letters not permitted'
    }
    if ($Number -match '[,:;]') {
        throw 'Punctuations not permitted'
    }

    $Number = $Number -replace '[^0-9]', ''

    if ($Number.Length -lt 10) {
        throw "Number can't be fewer than 10 digits"
    }
    if ($Number.Length -eq 11 -and $Number[0] -ne '1') {
        throw '11 digits must start with 1'
    }
    if ($Number.Length -gt 11) {
        throw "Number can't be more than 11 digits"
    }
    if ($Number.Length -eq 11 -and $Number[0] -eq '1') {
        $Number = $Number.Substring(1)
    }
    if ($Number[0] -eq '0' -or $Number[0] -eq '1') {
        throw "Area code can't start with " + $Number[0]
    }
    if ($Number[3] -eq '0' -or $Number[3] -eq '1') {
        throw "Exchange code can't start with " + $Number[3]
    }
    if ($Pretty) {
        $Number = $Number.Insert(0, '(').Insert(4, ')-').Insert(9, '-')
    }
    return $Number
}