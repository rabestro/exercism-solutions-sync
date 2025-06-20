BEGIN {
    FPAT = "..?.?"
    Stop = @/UAA|UAG|UGA/
    Protein["AUG"] = "Methionine"
    Protein["UUU|UUC"] = "Phenylalanine"
    Protein["UUA|UUG"] = "Leucine"
    Protein["UCU|UCC|UCA|UCG"] = "Serine"
    Protein["UAU|UAC"] = "Tyrosine"
    Protein["UGU|UGC"] = "Cysteine"
    Protein["UGG"] = "Tryptophan"
}
{
    for (i = 1; i<= NF; ++i)
        if ($i ~ Stop) NF = i - 1
        else $i = translate($i)
    print
}

function translate(rna,   codon) {
    for (codon in Protein) if (rna ~ codon) return Protein[codon]
    print "Invalid codon"; exit 1
}
