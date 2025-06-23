trait SimpleLinkedList[T]:
  def isEmpty: Boolean

  def value: T

  def add(item: T): SimpleLinkedList[T]

  def next: SimpleLinkedList[T]

  def reverse: SimpleLinkedList[T]

  def toSeq: Seq[T]


object SimpleLinkedList:
  def apply[T](xs: T*): SimpleLinkedList[T] = Node(None, null)

  def fromSeq[T](xs: Seq[T]): SimpleLinkedList[T] = xs match
    case Nil => Node(None, null)
    case head :: Nil => Node(Some(head), Node(None, null))
    case head :: tail => Node(Some(head), fromSeq(tail))
