package romannumerals

import "errors"

func ToRomanNumeral(number int) (string, error) {
	if number < 1 || number > 3000 {
		return "", errors.New("number has to be in range [1..3000]")
	}
	roman := []string{"", "M", "MM", "MMM"}[number/1000] + []string{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}[number%1000/100] + []string{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}[number%100/10] + []string{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}[number%10]
	return roman, nil
}
