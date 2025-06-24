object Darts {
  def score(x: Double, y: Double): Int =
    Math.sqrt(x * x + y * y) match
      case distance if distance > 10 => 0
      case distance if distance > 5 => 1
      case distance if distance > 1 => 5
      case _ => 10
}