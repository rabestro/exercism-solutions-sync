_DNA_RNA = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}


def to_rna(dna_strand):
    return "".join(_DNA_RNA[nucleotid] for nucleotid in dna_strand)
