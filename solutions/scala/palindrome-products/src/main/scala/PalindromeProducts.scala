case class PalindromeProducts(min: Int, max: Int):
  import PalindromeProducts.Result

  lazy val smallest: Option[Result] = palindromeProducts.minByOption(_._1)
  lazy val largest: Option[Result] = palindromeProducts.maxByOption(_._1)

  private lazy val palindromeProducts: Map[Int, Set[(Int, Int)]] =
    val pairs = for
      x <- (min to max).iterator
      y <- (x to max).iterator
      product = Math.multiplyExact(x, y)
      if isPalindrome(product)
    yield product -> (x -> y)
    pairs.toSet.groupMap(_._1)(_._2)

  private def isPalindrome(originalNumber: Int): Boolean =
    var number = originalNumber
    var reversed = 0L
    while number > 0 do
      reversed *= 10
      reversed += number % 10
      number /= 10
    reversed == originalNumber

object PalindromeProducts:
  private type Result = (Int, Set[(Int, Int)])
