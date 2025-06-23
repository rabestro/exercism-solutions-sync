import scala.annotation.tailrec

sealed trait SimpleLinkedList[T]:
  def isEmpty: Boolean

  def value: T

  def add(item: T): SimpleLinkedList[T]

  def next: SimpleLinkedList[T]

  def reverse: SimpleLinkedList[T]

  def toSeq: Seq[T]


object SimpleLinkedList:
  def apply[T](ts: T*): SimpleLinkedList[T] = fromSeq(ts)

  def fromSeq[T](ts: Seq[T]): SimpleLinkedList[T] =
    ts.foldRight(SimpleLinkedList.empty[T])(Node(_, _))

  private def empty[T]: SimpleLinkedList[T] = EmptyList[T]()

case object EmptyList extends SimpleLinkedList[Any]:
  def apply[T](): SimpleLinkedList[T] = this.asInstanceOf[SimpleLinkedList[T]]

  def unapply[T](xs: SimpleLinkedList[T]): Boolean = xs == this

  override def isEmpty: Boolean = true

  override def value: Any = throw new NoSuchElementException("value called on Empty")

  override def add(item: Any): SimpleLinkedList[Any] = Node(item, this)

  override def next: SimpleLinkedList[Any] = throw new NoSuchElementException("next called on Empty")

  override def reverse: SimpleLinkedList[Any] = this

  override def toSeq: Seq[Any] = Seq.empty

end EmptyList

case class Node[T](override val value: T,
                   override val next: SimpleLinkedList[T]) extends SimpleLinkedList[T]:
  override def isEmpty: Boolean = false

  override def add(item: T): SimpleLinkedList[T] =
    Node(item, reverse).reverse

  override def reverse: SimpleLinkedList[T] =
    @tailrec
    def loop(xs: SimpleLinkedList[T], ys: SimpleLinkedList[T]): SimpleLinkedList[T] =
      xs match
        case Node(x, xs) => loop(xs, Node(x, ys))
        case EmptyList => ys
    end loop

    loop(this, EmptyList())


  override def toSeq: Seq[T] = value +: next.toSeq

end Node
