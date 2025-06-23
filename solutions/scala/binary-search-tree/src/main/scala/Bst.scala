final case class Bst(value: Int, left: Option[Bst] = None, right: Option[Bst] = None):
  def insert(x: Int): Bst =
    if x <= value then
      Bst(value, left.map(_.insert(x)) orElse Some(Bst(x)), right)
    else
      Bst(value, left, right.map(_.insert(x)) orElse Some(Bst(x)))

object Bst:
  def fromList(list: List[Int]): Bst =
    list.tail.foldLeft(Bst(list.head))(_.insert(_))

  def toList(bst: Bst): List[Int] =
    bst.left.map(toList).getOrElse(Nil) ++ List(bst.value) ++ bst.right.map(toList).getOrElse(Nil)
