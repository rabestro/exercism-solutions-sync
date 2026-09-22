object Hamming:
  def distance(dnaStrandOne: String, dnaStrandTwo: String): Option[Int] =
    Option.when(dnaStrandOne.length == dnaStrandTwo.length):
      (dnaStrandOne lazyZip dnaStrandTwo).count(_ != _)