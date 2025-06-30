import scala.collection.immutable.BitSet

final case class CustomSet(data: BitSet)

object CustomSet:
  def fromList(list: List[Int]): CustomSet =
    new CustomSet(BitSet(list *))

  def empty(customSet: CustomSet): Boolean =
    customSet.data.isEmpty

  def member(customSet: CustomSet, element: Int): Boolean =
    customSet.data.contains(element)

  def isEqual(a: CustomSet, b: CustomSet): Boolean = a == b

  def isSubsetOf(a: CustomSet, b: CustomSet): Boolean =
    a.data.subsetOf(b.data)

  def isDisjointFrom(a: CustomSet, b: CustomSet): Boolean =
    a.data.intersect(b.data).isEmpty

  def union(a: CustomSet, b: CustomSet): CustomSet =
    new CustomSet(a.data | b.data)

  def intersection(a: CustomSet, b: CustomSet): CustomSet =
    new CustomSet(a.data & b.data)

  def difference(a: CustomSet, b: CustomSet): CustomSet =
    new CustomSet(a.data &~ b.data)

  def insert(customSet: CustomSet, element: Int): CustomSet =
    new CustomSet(customSet.data + element)
