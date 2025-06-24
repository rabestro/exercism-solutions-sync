enum NumberType:
  case Perfect, Abundant, Deficient

object PerfectNumbers:
  def classify(number: Int): Either[String, NumberType] =
    if number <= 0 then
      Left("Classification is only possible for natural numbers.")
    else
      val aliquotSum = sumFactors(number)
      number match
        case 1 => Right(NumberType.Deficient)
        case x if aliquotSum < x => Right(NumberType.Deficient)
        case x if x == aliquotSum => Right(NumberType.Perfect)
        case _ => Right(NumberType.Abundant)

  private def sumFactors(number: Int): Int =
    (2 to Math.sqrt(number).toInt)
      .filter(number % _ == 0)
      .foldLeft(1) { (sum, factor) =>
        val complementaryFactor = number / factor
        if factor != complementaryFactor then
          sum + factor + complementaryFactor
        else
          sum + factor
      }