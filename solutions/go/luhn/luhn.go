package luhn

import (
	"regexp"
	"strconv"
	"strings"
)

// Valid determines whether or not a number is valid per the Luhn formula.
func Valid(cardNumber string) bool {
	number := strings.ReplaceAll(cardNumber, " ", "")
	if matched, _ := regexp.MatchString(`^\d{2,}$`, number); !matched {
		return false
	}
	digits := strings.Split(number, "")

	sum := 0
	for isEven, i := false, len(digits)-1; i >= 0; i-- {
		digit, _ := strconv.Atoi(digits[i])
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
