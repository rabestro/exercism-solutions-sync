_DNA_RNA = str.maketrans("GCTA", "CGAU")


def to_rna(dna_strand: str) -> str:
    """Transcribe a DNA strand into its RNA equivalent."""
    return dna_strand.translate(_DNA_RNA)
