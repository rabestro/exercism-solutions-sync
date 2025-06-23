package bookstore

import (
	"math"
	"sort"
)

const series = 5

var discount = map[int]int{1: 0, 2: 5, 3: 10, 4: 20, 5: 25}

func Cost(booksInStore []int) int {
	books := make([]int, series)
	for _, book := range booksInStore {
		books[book-1]++
	}
	sort.Ints(books)

	batch := map[int]int{
		1: books[4] - books[3],
		2: books[3] - books[2],
		3: books[2] - books[1],
		4: books[1] - books[0],
		5: books[0],
	}

	correction := int(math.Min(float64(batch[3]), float64(batch[5])))
	batch[3] -= correction
	batch[4] += correction * 2
	batch[5] -= correction

	total := 0
	for booksInBatch, countOfBatch := range batch {
		total += 8 * booksInBatch * (100 - discount[booksInBatch]) * countOfBatch
	}
	return total
}
