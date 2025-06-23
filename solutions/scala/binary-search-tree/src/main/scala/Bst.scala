final case class Bst(value: Int, left: Option[Bst] = None, right: Option[Bst] = None):
  def insert(x: Int): Bst = if x <= value
  then copy(left = left.map(_.insert(x)) orElse Some(Bst(x)))
  else copy(right = right.map(_.insert(x)) orElse Some(Bst(x)))

object Bst:
  def fromList(list: List[Int]): Bst = list match
    case head :: tail => tail.foldLeft(Bst(head))(_.insert(_))
    case Nil => throw new IllegalArgumentException("Cannot create a BST from an empty list")

  def toList(bst: Bst): List[Int] =
    bst.left.toList.flatMap(toList) ::: bst.value :: bst.right.toList.flatMap(toList)
