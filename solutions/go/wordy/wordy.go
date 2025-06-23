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

	initialNumber, _ := strconv.Atoi(matches[1])
	r := Result{initialNumber}

	operations := operationPattern.FindAllStringSubmatch(question, -1)
	for _, operation := range operations {
		r.process(operation[1], operation[2])
	}
	return r.value, true
}
