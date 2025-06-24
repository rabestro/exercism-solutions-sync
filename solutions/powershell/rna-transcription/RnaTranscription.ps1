Function Invoke-RnaTranscription() {
    <#
    .SYNOPSIS
    Transcribe a DNA strand into RNA.

    .DESCRIPTION
    Given a DNA strand, return its RNA complement (per RNA transcription).

    .PARAMETER Strand
    The DNA strand to transcribe.

    .EXAMPLE
    Invoke-RnaTranscription -Strand "A"
    #>
    [CmdletBinding()]
    Param(
        [string]$Strand
    )
    
    # Define the nucleotide complements
    $complements = @{
        "G" = "C"
        "C" = "G"
        "T" = "A"
        "A" = "U"
    }

    $rna = ""
    foreach ($nucleotide in $Strand.ToCharArray()) {
        $rna += $complements[$nucleotide.ToString()]
    }
    return $rna
}
