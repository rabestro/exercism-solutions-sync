object Hamming:
  def distance(dnaStrandOne: String, dnaStrandTwo: String): Option[Int] =
    if dnaStrandOne.length != dnaStrandTwo.length then
      None
    else
      Some(dnaStrandOne zip dnaStrandTwo count { case (a, b) => a != b })
