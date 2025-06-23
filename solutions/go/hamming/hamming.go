// Package hamming is the solution to http://exercism.io/exercises/go/hamming
package hamming

import "errors"

/* Distance calculate the Hamming Distance between two DNA strands.

The Hamming distance is only defined for sequences of equal length, so an
attempt to calculate it between sequences of different lengths returns error.
*/
func Distance(a, b string) (int, error) {
	if len(a) != len(b) {
		return -1, errors.New("the sequences have to be equal length")
	}
	distance := 0
	for i := range a {
		if a[i] != b[i] {
			distance++
		}
	}
	return distance, nil
}
