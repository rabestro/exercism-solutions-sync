enum NumberType:
  case Perfect, Abundant, Deficient

object PerfectNumbers:
  def classify(number: Int): Either[String, NumberType] =
    Either.cond(number > 0,
      aliquotSum(number).compare(number).sign match
        case 0 => NumberType.Perfect
        case 1 => NumberType.Abundant
        case _ => NumberType.Deficient
      ,
      "Classification is only possible for natural numbers."
    )

  private def aliquotSum(number: Int): Long =
    if number == 1 then 0L
    else
      (2 to math.sqrt(number).toInt).view
        .filter(number % _ == 0)
        .flatMap(f => Set(f, number / f))
        .foldLeft(1L)(_ + _)