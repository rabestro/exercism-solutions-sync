from textwrap import wrap
from itertools import takewhile

PROTEIN_TO_CODONS = {
    'Methionine': ('AUG',),
    'Phenylalanine': ('UUU', 'UUC'),
    'Leucine': ('UUA', 'UUG'),
    'Serine': ('UCU', 'UCC', 'UCA', 'UCG'),
    'Tyrosine': ('UAU', 'UAC'),
    'Cysteine': ('UGU', 'UGC'),
    'Tryptophan': ('UGG',),
    'Stop': ('UAA', 'UAG', 'UGA'),
}

CODONS = {
    codon: protein
    for protein, codons in PROTEIN_TO_CODONS.items()
    for codon in codons
}


def proteins(strand: str) -> list[str]:
    """Translate an RNA strand into a sequence of proteins."""

    codons = (strand[i:i + 3] for i in range(0, len(strand), 3))
    protein_iterator = (CODONS[c] for c in codons)
    return list(takewhile(lambda p: p != 'Stop', protein_iterator))
