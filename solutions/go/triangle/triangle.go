// Package triangle contains solution to Triangle exercise
package triangle

import "math"

type Kind int

const (
	NaT Kind = iota // not a triangle
	Equ             // equilateral
	Iso             // isosceles
	Sca             // scalene
)

// KindFromSides determines if a triangle is equilateral, isosceles, or scalene.
func KindFromSides(a, b, c float64) Kind {
	var k Kind

	switch {
	case math.IsNaN(a) || math.IsNaN(b) || math.IsNaN(c):
	case math.IsInf(a, 0) || math.IsInf(b, 0) || math.IsInf(c, 0):
	case a <= 0 || b <= 0 || c <= 0:
	case a+b < c || a+c < b || b+c < a:
		k = NaT
	case a == b && b == c:
		k = Equ
	case a == b || a == c || b == c:
		k = Iso
	default:
		k = Sca
	}
	return k
}
