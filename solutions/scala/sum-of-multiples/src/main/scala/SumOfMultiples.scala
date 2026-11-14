object SumOfMultiples:
  def sum(factors: Set[Int], limit: Int): Int =
    factors.filter(f => f > 0).flatMap(factor => factor until limit by factor).sum
