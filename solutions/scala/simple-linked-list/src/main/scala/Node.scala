import SimpleLinkedList.fromSeq

case class Node[T](data: Option[T], next: SimpleLinkedList[T]) extends SimpleLinkedList[T]:
  override def isEmpty: Boolean = data.isEmpty

  override def value: T = data.get

  override def add(item: T): SimpleLinkedList[T] = fromSeq(toSeq :+ item)

  override def reverse: SimpleLinkedList[T] = fromSeq(toSeq.reverse)

  override def toSeq: Seq[T] = data match
    case None => Seq()
    case Some(x) => x +: next.toSeq
