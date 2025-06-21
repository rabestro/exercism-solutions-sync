object NthPrime:
  def prime(n: Int): Option[Int] =
    if n < 1 then None
    else Some(calculatePrime(n))

  private def calculatePrime(nth: Int): Int =
    val primes = scala.collection.mutable.ArrayBuffer(2)

    def isPrime(candidate: Int): Boolean =
      primes
        .takeWhile(_ <= Math.sqrt(candidate))
        .forall(candidate % _ != 0)

    LazyList.iterate(3)(_ + 2)
      .takeWhile(_ => primes.size < nth)
      .filter(isPrime)
      .foreach(primes += _)

    primes(nth - 1)
