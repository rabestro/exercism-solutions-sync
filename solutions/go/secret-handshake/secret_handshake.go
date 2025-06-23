package secret

func Handshake(code uint) []string {
	actions := []string{"wink", "double blink", "close your eyes", "jump"}

	var handshake []string
	for i, action := range actions {
		// We use bitwise AND operation to check if specific bit is set
		if (code>>i)&1 == 1 {
			handshake = append(handshake, action)
		}
	}

	// If the 5th bit is set we need to reverse the actions
	if (code>>4)&1 == 1 {
		for left, right := 0, len(handshake)-1; left < right; left, right = left+1, right-1 {
			handshake[left], handshake[right] = handshake[right], handshake[left]
		}
	}

	return handshake
}
