Function Get-Products()
{
    <#
    .SYNOPSIS
    Detect palindrome products in a given range.

    .DESCRIPTION
    A palindromic number is a number that remains the same when its digits are reversed.
    For example, `121` is a palindromic number but `112` is not.

    Given a range of numbers, find the largest and smallest palindromes which
    are products of two numbers within that range.

    Your solution should return the largest and smallest palindromes, along with the factors of each within the range.
    If the largest or smallest palindrome has more than one pair of factors within the range, then return all the pairs.

    .PARAMETER Min
    The minimum value of the range to find palindrome products.
    Min can't be larger than Max.
    
    .PARAMETER Max
    The maximum value of the range to find palindrome products.
    Max can't be smaller than Min. Parameter contrainst should be placed on this parameter.

    .PARAMETER Largest
    A switch parameter, present to return the largest palindrome.
    If not present then default behavior of the function should be return the smallest palindrome.

    .EXAMPLE
    Get-Products -Min 1 -Max 9
    Returns : @{ Value = 1; Products = @( @(1, 1)) }

    Get-Products -Min 1 -Max 9 -Largest
    Returns : @{ Value = 9; Products = @( @(1, 9), @(3, 3)) }
    #>
    [CmdletBinding()]
    Param(
        [Parameter(Mandatory = $true)]
        [int]$Min,
        [Parameter(Mandatory = $true)]
        [int]$Max,
        [switch]$Largest
    )
    if ($Min -gt $Max) {
        throw "Cannot validate argument on parameter 'Max'"
    }
    $result = @{
        Value = $Largest ? [int]::MinValue : [int]::MaxValue;
        Products = @()
    }
    $first = $Largest ? $Max..$Min : $Min..$Max
    foreach ($i in $first)
    {
        $second = $Largest ? $i..$Min : $i..$Max
        foreach ($j in $second)
        {
            $product = $i * $j
            $enough = $Largest ? $product -lt $result.Value : $product -gt $result.Value
            if ($enough)
            {
                break;
            }
            $stringNumber = [string]$product
            $reversedNumber = -join $stringNumber[-1..-($stringNumber.Length)]
            $isPalindrome = $stringNumber -eq $reversedNumber
            if (-not $isPalindrome)
            {
                continue;
            }
            $isRecord = $Largest ? $product -gt $result.Value : $product -lt $result.Value
            if ($isRecord)
            {
                $result.Value = $product
                $result.Products = @()
            }
            $factors = $Largest ? @($j, $i) : @($i, $j)
            $result.Products += @(,$factors )
        }
    }
    $result.Value = $result.Products.Length -eq 0 ? $null : $result.Value
    $result
}
