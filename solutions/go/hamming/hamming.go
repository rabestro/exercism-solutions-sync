package hamming

import "errors"

/* Calculate the Hamming Distance between two DNA strands.

The Hamming distance is only defined for sequences of equal length, so an
attempt to calculate it between sequences of different lengths returns error.
*/
func Distance(a, b string) (int, error) {
	if len(a) != len(b) {
		return 0, errors.New("the sequences have to be equal length")
	}
	count := 0
	for i := len(a) - 1; i >= 0; i-- {
		if a[i] != b[i] {
			count++
		}
	}
	return count, nil
}
