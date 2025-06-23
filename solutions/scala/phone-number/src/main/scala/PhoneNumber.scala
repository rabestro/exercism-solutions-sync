object PhoneNumber:
  private val telephoneNumber = "^1?([2-9]\\d{2}[2-9]\\d{6})$".r

  def clean(input: String): Option[String] =
    telephoneNumber.findFirstMatchIn(input.filter(_.isDigit)).map(_.group(1))
