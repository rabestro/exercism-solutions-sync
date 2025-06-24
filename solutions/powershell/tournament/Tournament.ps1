Function Invoke-Tournament {
    <#
    .SYNOPSIS
    Tally the results of a small football competition.

    .DESCRIPTION
    Given an array of string containing which team played against which and what the outcome was, create a tally table.

    .PARAMETER Results
    An array of the string, each line represent a match being played and its outcome.

    .EXAMPLE
    Invoke-Tournament -Results @("Annalyn;Elyses;win")
    
    return:
    @"
    Team                           | MP |  W |  D |  L |  P
    Annalyn                        |  1 |  1 |  0 |  0 |  3
    Elyses                         |  1 |  0 |  0 |  1 |  0
    "@
    #>
    [CmdletBinding()]
    Param(
        [string[]]$Results
    )

    class Team {
        [string]$Name
        [int]$MP
        [int]$W
        [int]$D
        [int]$L
        [int]$P
    }
    $Table = @{}

    $Results | 
    ForEach-Object {
        $team1, $team2, $result = $_ -split ';'
        if (-not $Table.ContainsKey($team1)) {
            $Table[$team1] = [Team]::new()
            $Table[$team1].Name = $team1
        }
        if (-not $Table.ContainsKey($team2)) {
            $Table[$team2] = [Team]::new()
            $Table[$team2].Name = $team2
        }
        switch ($result) {
            win { 
                $Table[$team1].W += 1
                $Table[$team2].L += 1 
                $Table[$team1].P += 3
            }
            loss { 
                $Table[$team1].L += 1
                $Table[$team2].W += 1  
                $Table[$team2].P += 3
            }
            draw {  
                $Table[$team1].D += 1
                $Table[$team2].D += 1
                $Table[$team1].P += 1
                $Table[$team2].P += 1
            }
        }
        $Table[$team1].MP += 1
        $Table[$team2].MP += 1
    }

    $report = @("Team                           | MP |  W |  D |  L |  P")
    $Table.Values | 
    Sort-Object -Property @{Expression = "P"; Descending = $true}, @{Expression = "Name"} | 
    ForEach-Object {
        $report += ("{0,-30} | {1,2} | {2,2} | {3,2} | {4,2} | {5,2}" -f $_.Name, $_.MP, $_.W, $_.D, $_.L, $_.P)
    }
    $report -join "`n"
}
