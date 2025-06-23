package isbn

import (
	"unicode"
)

func IsValidISBN(isbn string) bool {
	number := 0
	position := 10
	for _, r := range isbn {
		switch {
		case position == 0:
			return false
		case r == '-':
			continue
		case unicode.IsDigit(r):
			digit := int(r - '0')
			number += digit * position
			position--
		case r == 'X' && position == 1:
			number += 10
			position--
		default:
			return false
		}
	}
	return position == 0 && number%11 == 0
}
