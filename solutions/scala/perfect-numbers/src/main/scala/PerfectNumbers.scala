enum NumberType:
  case Perfect, Abundant, Deficient

object PerfectNumbers:
  def classify(number: Int): Either[String, NumberType] =
    if number <= 0 then
      Left("Classification is only possible for natural numbers.")
    else if number == 1 then
      Right(NumberType.Deficient)
    else
      Right {
        aliquotSum(number) match {
          case sum if sum < number => NumberType.Deficient
          case sum if sum == number => NumberType.Perfect
          case sum if sum > number => NumberType.Abundant
        }
      }

  private def aliquotSum(number: Int): Int =
    (2 to Math.sqrt(number).toInt)
      .filter(number % _ == 0)
      .foldLeft(1) { (sum, factor) =>
        val complementaryFactor = number / factor
        sum + factor + (if factor != complementaryFactor then complementaryFactor else 0)
      }