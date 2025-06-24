Function Invoke-ProteinTranslation() {
    <#
    .SYNOPSIS
    Translate RNA sequences into proteins.

    .DESCRIPTION
    Take an RNA sequence and convert it into condons and then into the name of the proteins in the form of a list.

    .PARAMETER Strand
    The RNA sequence to translate.

    .EXAMPLE
    Invoke-ProteinTranslation -Strand "AUG"
    #>
    [CmdletBinding()]
    Param(
        [string]$Strand
    )

    $proteinMap = @{
        "AUG" = "Methionine"
        "UUC" = "Phenylalanine"
        "UUU" = "Phenylalanine"
        "UUA" = "Leucine"
        "UUG" = "Leucine"
        "UCU" = "Serine"
        "UCC" = "Serine"
        "UCA" = "Serine"
        "UCG" = "Serine"
        "UAU" = "Tyrosine"
        "UAC" = "Tyrosine"
        "UGC" = "Cysteine"
        "UGU" = "Cysteine"
        "UGG" = "Tryptophan"
        "UAA" = "STOP"
        "UAG" = "STOP"
        "UGA" = "STOP"
    }

    $chunks = $Strand -split '(...)' | Where-Object { $_ -ne "" }

    # Translate each chunk into a protein
    $proteins = $chunks | ForEach-Object {
        if ($proteinMap.ContainsKey($_)) {
            $proteinMap[$_]
        } else {
            throw "*error: Invalid codon*"
        }
    }

    # Take proteins until "STOP"
    $result = @()
    foreach ($protein in $proteins) {
        if ($protein -eq "STOP") {
            break
        }
        $result += $protein
    }

    # Return the list of proteins
    return $result
}
