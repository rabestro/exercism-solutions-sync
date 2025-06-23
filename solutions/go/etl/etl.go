package etl

import "strings"

// Transform transforms the legacy data format to the shiny new format.
func Transform(oldFormat map[int][]string) map[string]int {
	newFormat := make(map[string]int)
	for score, letters := range oldFormat {
		for _, letter := range letters {
			newFormat[strings.ToLower(letter)] = score
		}
	}
	return newFormat
}
