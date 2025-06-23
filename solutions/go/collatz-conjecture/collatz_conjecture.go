package collatzconjecture

import "fmt"

// CollatzConjecture computes the number of steps to reach 1, given a starting number
// according to the rules of the Collatz Conjecture.
// Returns an error if the provided number is less than 1.
func CollatzConjecture(n int) (int, error) {
	if n < 1 {
		return 0, fmt.Errorf("Input must be a natural number greater than 0 but got %d", n)
	}
	step := 0
	for ; n > 1; step++ {
		n = calculateNextNumber(n)
	}
	return step, nil
}

// calculateNextNumber takes an integer n and calculates the next number according to the rules of the Collatz Conjecture.
func calculateNextNumber(n int) int {
	if n%2 == 0 {
		return n / 2
	}
	return n*3 + 1
}
