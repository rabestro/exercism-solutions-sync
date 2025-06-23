package hamming

import "errors"

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
