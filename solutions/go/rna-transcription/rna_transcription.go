package strand

import "strings"

var dnaToRna = map[rune]rune{
	'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U',
}

func ToRNA(dna string) string {
	return strings.Map(func(r rune) rune { return dnaToRna[r] }, dna)
}
