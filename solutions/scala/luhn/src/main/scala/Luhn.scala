object Luhn:
  def valid(numberToValidate: String): Boolean =
    val number = numberToValidate.replace(" ", "")
    if !number.matches("\\d{2,}") then false
    else
      number.map(_.asDigit).reverse.zipWithIndex.map { case (digit, index) =>
        if index % 2 == 0 then digit
        else
          val x = digit * 2
          if x > 9 then x - 9 else x
      }.sum % 10 == 0
