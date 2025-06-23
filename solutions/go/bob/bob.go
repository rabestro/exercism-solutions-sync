// Package bob implements a solution for the bob exercise from the Exercism Go track.
package bob

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
