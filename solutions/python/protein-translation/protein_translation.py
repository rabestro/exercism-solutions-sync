from textwrap import wrap

CODONS = {
    'AUG': 'Methionine',
    'UUU': 'Phenylalanine',
    'UUC': 'Phenylalanine',
    'UUA': 'Leucine',
    'UUG': 'Leucine',
    'UCU': 'Serine',
    'UCC': 'Serine',
    'UCA': 'Serine',
    'UCG': 'Serine',
    'UAU': 'Tyrosine',
    'UAC': 'Tyrosine',
    'UGU': 'Cysteine',
    'UGC': 'Cysteine',
    'UGG': 'Tryptophan',
    'UAA': 'Stop',
    'UAG': 'Stop',
    'UGA': 'Stop',
}


def proteins(strand: str) -> list[str]:
    result = []
    for codon in wrap(strand, 3):
        protein = CODONS[codon]
        if protein == 'Stop':
            break
        result.append(protein)
    return result
