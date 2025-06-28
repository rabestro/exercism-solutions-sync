class EmptyBufferException() extends Exception

class FullBufferException() extends Exception

class CircularBuffer(var capacity: Int)
  extends scala.collection.mutable.ArrayDeque[Int](capacity):

  def write(value: Int): Unit =
    if size == capacity then throw new FullBufferException
    addOne(value)

  def read(): Int =
    if isEmpty then throw new EmptyBufferException
    removeHead()

  def overwrite(value: Int): Unit =
    if size == capacity then removeHead()
    addOne(value)
