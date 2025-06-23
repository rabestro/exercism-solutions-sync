package isogram

import (
	"strings"
	"unicode"
)

// IsIsogram determines if a word or phrase is an isogram.
func IsIsogram(word string) bool {
	var bitset uint32 = 0
	for _, c := range strings.ToLower(word) {
		if unicode.IsLetter(c) {
			if bitset&(1<<(c-'a')) > 0 {
				return false
			} else {
				bitset |= 1 << (c - 'a')
			}
		}
	}
	return true
}
