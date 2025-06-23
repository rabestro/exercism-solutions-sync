// Package raindrops is the solution to http://exercism.io/exercises/go/raindrops
package raindrops

import "strconv"

// Convert - convert a number into a string that contains raindrop sounds corresponding to certain potential factors.
func Convert(drops int) string {
	var output string
	if drops%3 == 0 {
		output += "Pling"
	}
	if drops%5 == 0 {
		output += "Plang"
	}
	if drops%7 == 0 {
		output += "Plong"
	}
	if len(output) == 0 {
		output = strconv.Itoa(drops)
	}
	return output
}
