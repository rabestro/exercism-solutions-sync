object Grains {
  val total: BigInt = BigInt(2).pow(64) - 1

  def square(number: Int): Option[BigInt] =
    if (number < 1 || number > 64) None 
    else Some(BigInt(2).pow(number - 1))
}