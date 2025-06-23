package wordy

import (
	"regexp"
	"strconv"
)

var (
	pattern          = regexp.MustCompile(`What is (-?\d+)(?: (?:plus|minus|multiplied by|divided by) -?\d+)*\?`)
	operationPattern = regexp.MustCompile(`(plus|minus|multiplied by|divided by) (-?\d+)`)
)

func Answer(question string) (int, bool) {
	matches := pattern.FindStringSubmatch(question)
	if matches == nil {
		return 0, false
	}

	result, _ := strconv.Atoi(matches[1])

	operations := operationPattern.FindAllStringSubmatch(question, -1)
	for _, operation := range operations {
		operator := operation[1]
		number, _ := strconv.Atoi(operation[2])
		switch operator {
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

	return result, true
}
