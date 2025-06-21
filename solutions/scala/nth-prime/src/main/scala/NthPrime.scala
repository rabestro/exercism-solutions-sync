object NthPrime:
  private val primes = 2 #:: LazyList.from(3, 2).filter(isPrime)

  private def isPrime(candidate: Int): Boolean =
    primes.takeWhile(_ <= math.sqrt(candidate)).forall(candidate % _ != 0)

  def prime(nth: Int): Option[Int] =
    if nth < 1 then None else Some(primes(nth - 1))
