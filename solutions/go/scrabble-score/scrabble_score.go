// Package raindrops is the solution to Scrabble Score exercise
package scrabble

import "strings"

// Score computes the Scrabble score for a word.
func Score(word string) int {
	score := 0
	for _, letter := range strings.ToUpper(word) {
		score += [26]int{1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10}[letter-'A']
	}
	return score
}
