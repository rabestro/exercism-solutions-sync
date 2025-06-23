// Package bob implements a solution for the bob exercise from the Exercism Go track.
package bob

import (
	"strings"
	"unicode"
)

type Remark string

func newRemark(remark string) Remark {
	return Remark(strings.TrimSpace(remark))
}
func (remark Remark) isSilence() bool {
	return remark == ""
}
func (remark Remark) isShouting() bool {
	hasLetters := strings.IndexFunc(string(remark), unicode.IsLetter) >= 0
	isUpcased := strings.ToUpper(string(remark)) == string(remark)
	return hasLetters && isUpcased
}
func (remark Remark) isQuestion() bool {
	return strings.HasSuffix(string(remark), "?")
}
func (remark Remark) isExasperated() bool {
	return remark.isShouting() && remark.isQuestion()
}

// Hey takes a message as input, and returns Bob's response to that remark.
func Hey(message string) string {
	remark := newRemark(message)

	switch {
	case remark.isSilence():
		return "Fine. Be that way!"
	case remark.isExasperated():
		return "Calm down, I know what I'm doing!"
	case remark.isShouting():
		return "Whoa, chill out!"
	case remark.isQuestion():
		return "Sure."
	default:
		return "Whatever."
	}
}
