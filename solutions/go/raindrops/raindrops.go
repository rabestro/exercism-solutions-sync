// The package contains function Convert
package raindrops

import "fmt"

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
		output = fmt.Sprint(drops)
	}
	return output
}
