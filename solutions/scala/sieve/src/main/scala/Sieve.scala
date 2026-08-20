object Sieve:
  def primes(limit: Int): List[Int] =
    val unmarkedNumbers = scala.collection.mutable.BitSet.fromSpecific(2 to limit)
    val limitSqrt = scala.math.sqrt(limit).toInt
    
    for
      candidate <- 2 to limitSqrt
      if unmarkedNumbers.contains(candidate)
      multiple <- candidate * candidate to limit by candidate
    do
      unmarkedNumbers -= multiple

    unmarkedNumbers.toList      
