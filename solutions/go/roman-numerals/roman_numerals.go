// Package romannumerals provides methods for manipulating numbers into roman numerals
package romannumerals

import "errors"

type RomanNumeral struct {
	Value  int
	Symbol string
}

// RomanNumerals is the mapping between numbers and their corresponding Roman numerals
var RomanNumerals = []RomanNumeral{
	{1000, "M"},
	{900, "CM"},
	{500, "D"},
	{400, "CD"},
	{100, "C"},
	{90, "XC"},
	{50, "L"},
	{40, "XL"},
	{10, "X"},
	{9, "IX"},
	{5, "V"},
	{4, "IV"},
	{1, "I"},
}

// ToRomanNumeral converts an integer to its roman numeral string equivalent
func ToRomanNumeral(number int) (string, error) {
	if number < 1 || number > 3999 {
		return "", errors.New("number has to be in range [1..3999]")
	}
	roman := ""
	for _, numeral := range RomanNumerals {
		for number >= numeral.Value {
			number -= numeral.Value
			roman += numeral.Symbol
		}
	}
	return roman, nil
}
