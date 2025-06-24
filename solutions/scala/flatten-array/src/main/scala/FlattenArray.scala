object FlattenArray:
  def flatten(array: List[Any]): List[Any] =
    array.flatMap {
      case a: List[Any] => flatten(a)
      case a => List(a)
    }.filterNot(_ == null)