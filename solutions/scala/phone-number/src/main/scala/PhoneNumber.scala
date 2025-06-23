object PhoneNumber:
  private val TelephoneNumber = "(?<=^1?)[2-9]\\d{2}[2-9]\\d{6}$".r

  def clean(input: String): Option[String] =
    TelephoneNumber.findFirstIn(input.filter(_.isDigit))
