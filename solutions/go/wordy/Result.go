package wordy

import "strconv"

type Result struct {
	value int
}

func (result *Result) process(operator, operand string) {
	number, _ := strconv.Atoi(operand)
	switch operator {
	case "plus":
		result.value += number
	case "minus":
		result.value -= number
	case "multiplied by":
		result.value *= number
	case "divided by":
		result.value /= number
	}
}
