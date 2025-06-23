// Package darts has a utility to calculate points earned in a game of darts
package darts

// Score computes the result for a throw.
func Score(x, y float64) int {
	distance := x*x + y*y
	switch {
	case distance > 100:
		return 0
	case distance > 25:
		return 1
	case distance > 1:
		return 5
	default:
		return 10
	}
}
