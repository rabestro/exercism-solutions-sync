case class Triangle(a: Double, b: Double, c: Double) {
  private val isValidTriangle =
    a > 0 && b > 0 && c > 0 && a + b > c && a + c > b && b + c > a

  val equilateral: Boolean =
    isValidTriangle && a == b && b == c

  val isosceles: Boolean =
    isValidTriangle && (a == b || b == c || a == c)

  val scalene: Boolean =
    isValidTriangle && a != b && b != c && a != c
}