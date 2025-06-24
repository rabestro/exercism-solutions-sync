object PythagoreanTriplet:
  def isPythagorean(candidate: (Int, Int, Int)): Boolean =
    val (a, b, c) = candidate
    a < b && b < c && a * a + b * b == c * c

  def pythagoreanTriplets(min: Int, max: Int): Seq[(Int, Int, Int)] =
    for {
      a <- min to max - 2
      b <- a + 1 to math.sqrt(max * max - a * a).toInt
      c = math.sqrt(a * a + b * b)
      if c.isValidInt
      if b < c.toInt
    } yield (a, b, c.toInt)

  def pythagoreanTripletsSum(sum: Int): Seq[(Int, Int, Int)] =
    for {
      a <- 1 to sum / 3
      b <- a + 1 to (sum - a - 1) / 2
      c = sum - a - b
      candidate = (a, b, c)
      if isPythagorean(candidate)
    } yield candidate

