object BookStore:
  private val BookPrice = Map(1 -> 800, 2 -> 760, 3 -> 720, 4 -> 640, 5 -> 600)
  private val SeriesCount = 5

  def total(books: List[Int]): Int =
    // Count the occurrences of each book series
    val seriesCounts = (1 to SeriesCount).map(i => books.count(_ == i)).sorted.toArray

    // Calculate the differences between each count and the previous one, reversed
    val groupCounts = (0 until SeriesCount).map { i =>
      if i == 0 then seriesCounts(0) else seriesCounts(i) - seriesCounts(i - 1)
    }.reverse.toArray

    // Rearrange books in groups to get maximum discount
    val combined = math.min(groupCounts(2), groupCounts(4))
    val adjustedGroups = groupCounts
      .updated(4, groupCounts(4) - combined)
      .updated(2, groupCounts(2) - combined)
      .updated(3, groupCounts(3) + 2 * combined)

    // Calculate the total price with maximum discount
    (1 to SeriesCount).map { i => i * BookPrice(i) * adjustedGroups(i - 1) }.sum
