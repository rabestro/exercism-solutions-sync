import scala.annotation.tailrec

object BinarySearch:
  def find(numbers: List[Number], target: Number): Option[Int] =
    @tailrec
    def binarySearch(low: Int = 0, high: Int = numbers.size - 1): Option[Int] =
      if low > high then None
      else
        val mid = (low + high) / 2
        numbers(mid).doubleValue() - target.doubleValue() match
          case 0 => Some(mid)
          case x if x > 0 => binarySearch(low, mid - 1)
          case _ => binarySearch(mid + 1, high)

    binarySearch()
