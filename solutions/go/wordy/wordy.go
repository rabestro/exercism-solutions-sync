package wordy

import (
	"regexp"
	"strconv"
)

var pattern = regexp.MustCompile(`(What is) (-?\d+)(?: (plus|minus|multiplied by|divided by) (-?\d+))*\?`)
var tokens = regexp.MustCompile(`-?\d+|is|plus|minus|multiplied by|divided by`)

func Answer(question string) (int, bool) {
	if !pattern.MatchString(question) {
		return 0, false
	}
	var result int64
	var operator string

	for i, token := range tokens.FindAllString(question, -1) {
		if i%2 == 0 {
			operator = token
			continue
		}
		number, _ := strconv.ParseInt(token, 10, 64)
		switch operator {
		case "is":
			result = number
		case "plus":
			result += number
		case "minus":
			result -= number
		case "multiplied by":
			result *= number
		case "divided by":
			result /= number
		}
	}
	return int(result), true
}
