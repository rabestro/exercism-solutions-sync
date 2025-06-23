// Package leap contains a function that says if a year is leap or not
package leap

// IsLeapYear returns true if given a year is a leap year, otherwise false.
func IsLeapYear(year int) bool {
	return year%400 == 0 || (year%4 == 0 && year%100 > 0)
}
