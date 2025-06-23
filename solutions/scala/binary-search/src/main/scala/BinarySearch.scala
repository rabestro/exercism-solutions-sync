import scala.annotation.tailrec

object BinarySearch:
  def find(numbers: List[Number], target: Number): Option[Int] =
    @tailrec
    def binarySearchTail(low: Int, high: Int): Option[Int] =
      if low > high then None
      else
        val mid = (low + high) / 2
        if numbers(mid) == target then Some(mid)
        else if numbers(mid).doubleValue() > target.doubleValue()
        then binarySearchTail(low, mid - 1)
        else binarySearchTail(mid + 1, high)

    binarySearchTail(0, numbers.size - 1)
