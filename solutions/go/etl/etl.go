package etl

import "strings"

// Transform transforms the legacy data format to the shiny new format.
func Transform(m map[int][]string) map[string]int {
	result := make(map[string]int)
	for v := range m {
		for _, c := range m[v] {
			result[strings.ToLower(c)] = v
		}
	}
	return result
}
