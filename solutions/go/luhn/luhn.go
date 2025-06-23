package luhn

import (
	"strconv"
	"strings"
)

// Valid determines whether or not a number is valid per the Luhn formula.
func Valid(cardNumber string) bool {
	number := strings.ReplaceAll(cardNumber, " ", "")
	if _, err := strconv.Atoi(number); err != nil || len(number) < 2 {
		return false
	}
	sum := 0
	isEven := len(number)%2 == 0
	for _, symbol := range number {
		digit := symbol - '0'
		if isEven {
			digit *= 2
			if digit > 9 {
				digit -= 9
			}
		}
		isEven = !isEven
		sum += int(digit)
	}
	return sum%10 == 0
}
