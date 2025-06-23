// Package bob implements a solution for the bob exercise from the Exercism Go track.
package bob

import (
	"strings"
)

// Hey takes a remark as input, and returns Bob's response to that remark.
func Hey(remark string) string {
	remark = strings.TrimSpace(remark)

	if remark == "" {
		return "Fine. Be that way!"
	}

	isQuestion := strings.HasSuffix(remark, "?")
	isYelling := strings.ToUpper(remark) == remark && strings.ToLower(remark) != remark

	switch {
	case isQuestion && isYelling:
		return "Calm down, I know what I'm doing!"
	case isYelling:
		return "Whoa, chill out!"
	case isQuestion:
		return "Sure."
	default:
		return "Whatever."
	}
}
