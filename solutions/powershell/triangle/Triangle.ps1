Enum Triangle {
    EQUILATERAL
    ISOSCELES
    SCALENE
}

Function Get-Triangle() {
    <#
    .SYNOPSIS
    Determine if a triangle is equilateral, isosceles, or scalene.

    .DESCRIPTION
    Given 3 sides of a triangle, return the type of that triangle if it is a valid triangle.
    
    .PARAMETER Sides
    The lengths of a triangle's sides.

    .EXAMPLE
    Get-Triangle -Sides @(1,2,3)
    Return: [Triangle]::SCALENE
    #>
    
    [CmdletBinding()]
    Param (
        [double[]]$Sides
    )

    if ($Sides | Where-Object { $_ -le 0 }) {
        throw 'All side lengths must be positive.' 
    }
    $measure = $Sides | Measure-Object -Maximum -Sum
    if ($measure.Maximum * 2 -gt $measure.Sum) {
        throw 'Side lengths violate triangle inequality.'
    }
    if ($Sides[0] -eq $Sides[1] -and $Sides[1] -eq $Sides[2]) {
        return [Triangle]::EQUILATERAL
    }
    if ($Sides[0] -eq $Sides[1] -or $Sides[1] -eq $Sides[2] -or $Sides[0] -eq $Sides[2]) {
        return [Triangle]::ISOSCELES
    }
    return [Triangle]::SCALENE
}