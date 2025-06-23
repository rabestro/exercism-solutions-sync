package chessboard

// Declare a type named File which stores if a square is occupied by a piece - this will be a slice of bools
type File [8]bool

// Declare a type named Chessboard which contains a map of eight Files, accessed with keys from "A" to "H"
type Chessboard map[string]File

// CountInFile returns how many squares are occupied in the chessboard,
// within the given file.
func CountInFile(cb Chessboard, file string) int {
	squares := 0
	for _, isOccupied := range cb[file] {
		if isOccupied {
			squares++
		}
	}
	return squares
}

// CountInRank returns how many squares are occupied in the chessboard,
// within the given rank.
func CountInRank(cb Chessboard, rank int) int {
	if rank < 1 || rank > 8 {
		return 0
	}
	squares := 0
	index := rank - 1
	for _, file := range cb {
		if file[index] {
			squares++
		}
	}
	return squares
}

// CountAll should count how many squares are present in the chessboard.
func CountAll(cb Chessboard) int {
	return 64
}

// CountOccupied returns how many squares are occupied in the chessboard.
func CountOccupied(cb Chessboard) int {
	squares := 0
	for _, file := range cb {
		for _, isOccupied := range file {
			if isOccupied {
				squares++
			}
		}
	}
	return squares
}
