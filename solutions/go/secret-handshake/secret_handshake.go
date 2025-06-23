package secret

const reverseSignsBit = 16

var signs = []string{"wink", "double blink", "close your eyes", "jump"}
var secretSigns = len(signs)

func Handshake(code uint) []string {
	action, increment, end := 0, 1, secretSigns

	if (code & reverseSignsBit) != 0 {
		action, increment, end = secretSigns-1, -1, -1
	}

	var output []string
	for ; action != end; action += increment {
		if (code & (1 << action)) != 0 {
			output = append(output, signs[action])
		}
	}
	return output
}
