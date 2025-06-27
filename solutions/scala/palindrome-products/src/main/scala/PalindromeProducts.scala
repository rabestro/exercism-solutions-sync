case class PalindromeProducts(a: Int, b: Int):
  private type Result = (Int, Set[(Int, Int)])
  lazy val smallest: Option[Result] = products.keys.minOption.map(toSet)
  lazy val largest: Option[Result] = products.keys.maxOption.map(toSet)

  private def toSet(key: Int): Result = key -> products(key).toSet

  private lazy val products =
    val seq = for {
      x <- a to b
      y <- x to b
      product = x * y
      if isPalindrome(product)
    } yield product -> (x -> y)
    seq.groupMap(_._1)(_._2)

  private def isPalindrome(originalNumber: Int): Boolean =
    var number = originalNumber
    var reversed = 0L
    while number > 0 do
      reversed *= 10
      reversed += number % 10
      number /= 10
    reversed == originalNumber
