import scala.annotation.tailrec

object PrimeFactors:
  def factors(n: Long): List[Long] =
    def nextPrime(current: Long): Long =
      if current == 2 then 3 else current + 2

    @tailrec
    def iter(number: Long, prime: Long, acc: List[Long]): List[Long] =
      if number == 1 then acc
      else if prime * prime > number then number :: acc
      else if number % prime == 0 then iter(number / prime, prime, prime :: acc)
      else iter(number, nextPrime(prime), acc)

    iter(n, 2L, List()).reverse
