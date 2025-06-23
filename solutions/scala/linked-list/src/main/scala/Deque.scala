import scala.collection.mutable

class Deque[T]:
  private val deque = new mutable.ArrayDeque[T]()
  def push(x: T): Unit = deque.append(x)
  def pop: Option[T] = deque.removeLastOption()
  def shift: Option[T] = deque.removeHeadOption()
  def unshift(x: T): Unit = deque.prepend(x)
