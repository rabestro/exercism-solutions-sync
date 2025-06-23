// Package proverb provides a function for generating the lines of a proverbial rhyme;
// "For want of a nail the kingdom was lost."
// The function can handle any list of inputs, generating the rhyme accordingly.
package proverb

// Proverb generates a proverbial rhyme from a given list of inputs.
// Each item in the input list generates a line in the resulting rhyme,
// following the pattern "For want of a X the Y was lost.",
// where X is the current item in the list and Y is the next item.
// The last line of the rhyme always follows the pattern "And all for the want of a X.",
// where X is the first item in the list.
// If the input list is empty, the resulting rhyme is also empty.
func Proverb(rhyme []string) []string {
	if len(rhyme) == 0 {
		return []string{}
	}

	proverb := make([]string, len(rhyme))
	for i := 0; i < len(rhyme)-1; i++ {
		proverb[i] = "For want of a " + rhyme[i] + " the " + rhyme[i+1] + " was lost."
	}

	proverb[len(rhyme)-1] = "And all for the want of a " + rhyme[0] + "."
	return proverb
}
