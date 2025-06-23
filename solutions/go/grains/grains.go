package grains

import "errors"

// Square calculates the number of grains of wheat on a chessboard
// given that the number on each square doubles.
func Square(n int) (uint64, error) {
	if n < 1 || n > 64 {
		return 0, errors.New("n out of range 1..64")
	}
	return 1 << (n - 1), nil
}

// Total returns the total number of grains on the chessboard
func Total() uint64 {
	return 0xFFFFFFFFFFFFFFFF
}
