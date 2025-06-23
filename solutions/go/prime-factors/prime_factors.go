package prime

import "math"

func Factors(number int64) []int64 {
	var factors []int64

	for number&1 == 0 {
		factors = append(factors, 2)
		number /= 2
	}

	max := int64(math.Sqrt(float64(number)))
	for i := int64(3); i <= max; i += 2 {
		for number%i == 0 {
			factors = append(factors, i)
			number /= i
		}
	}

	if number > 2 {
		factors = append(factors, number)
	}
	return factors
}
