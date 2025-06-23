case class Bst[T: Ordering](value: T, left: Option[Bst[T]] = None, right: Option[Bst[T]] = None):
  private val ord = Ordering[T]

  def insert(v: T): Bst[T] = if ord.lteq(v, value)
  then copy(left = left.map(_.insert(v)) orElse Some(Bst(v)))
  else copy(right = right.map(_.insert(v)) orElse Some(Bst(v)))

object Bst:
  def fromList[T: Ordering](list: List[T]): Bst[T] = list match
    case head :: tail => tail.foldLeft(Bst(head))(_.insert(_))
    case Nil => throw new IllegalArgumentException("Cannot create a BST from an empty list")

  def toList[T: Ordering](bst: Bst[T]): List[T] =
    bst.left.toList.flatMap(toList) ::: bst.value :: bst.right.toList.flatMap(toList)
