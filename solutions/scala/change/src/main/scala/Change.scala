import scala.collection.{SortedSet, mutable}

object Change:
  def findFewestCoins(amount: Int, denominations: List[Int]): Option[List[Int]] =
    optimal(amount, SortedSet.from(denominations))

  private def optimal(amount: Int, denominations: SortedSet[Int]): Option[List[Int]] =
    val changes = mutable.HashMap[Int, List[Int]](0 -> Nil)
    val amounts = mutable.ArrayDeque[Int](0)

    while (amounts.nonEmpty)
      val head = amounts.removeHead()
      val value = changes.get(head)
      if head == amount then
        return value.map(_.reverse)

      denominations.foreach { coin =>
        val total = head + coin
        if total <= amount && !changes.contains(total) then
          amounts addOne total
          val change = coin :: value.getOrElse(Nil)
          changes.put(total, change)
      }
    None

  private def greedy(amount: Int, denominations: SortedSet[Int]): Option[List[Int]] =
    def change(amount: Int, result: List[Int]): Option[List[Int]] =
      if amount == 0 then Option(result)
      else denominations.maxBefore(amount + 1)
        .flatMap(coin => change(amount - coin, coin :: result))

    change(amount, Nil)
