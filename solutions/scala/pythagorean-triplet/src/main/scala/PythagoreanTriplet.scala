object PythagoreanTriplet:
  private val smallestPossibleA = 3

  def isPythagorean(candidate: (Int, Int, Int)): Boolean =
    val (a, b, c) = candidate
    a < b && b < c && a * a + b * b == c * c

  def pythagoreanTriplets(min: Int, max: Int): Seq[(Int, Int, Int)] =
    for {
      a <- min to max - 2
      bMax = math.sqrt(max * max - a * a).toInt
      b <- a + 1 to bMax
      c = math.sqrt(a * a + b * b)
      if c.isValidInt
      if b < c.toInt
    } yield (a, b, c.toInt)

  def pythagoreanTripletsSum(sum: Int): Seq[(Int, Int, Int)] =
    for {
      a <- smallestPossibleA to sum / 3
      numerator = (sum * sum) - (2 * sum * a)
      denominator = 2 * (sum - a)
      remainder = numerator % denominator
      if remainder == 0
      b = numerator / denominator
      if a < b
      c = sum - a - b
    } yield (a, b, c)

