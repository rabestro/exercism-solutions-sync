package luhn

import (
	"strconv"
	"strings"
)

// Valid determines whether or not a number is valid per the Luhn formula.
func Valid(cardNumber string) bool {
	number := strings.ReplaceAll(cardNumber, " ", "")

	if num, err := strconv.Atoi(number); err == nil && len(number) > 1 {
		sum := 0
		for isEven := false; num > 0; num /= 10 {
			digit := num % 10
			if isEven {
				digit *= 2
				if digit > 9 {
					digit -= 9
				}
			}
			isEven = !isEven
			sum += digit
		}
		return sum%10 == 0
	}
	return false
}
