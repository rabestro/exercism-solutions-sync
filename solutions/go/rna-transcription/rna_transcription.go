package strand

import "strings"

var dnaToRna = map[rune]rune{'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

func translator(r rune) rune { return dnaToRna[r] }

func ToRNA(dna string) string {
	return strings.Map(translator, dna)
}
