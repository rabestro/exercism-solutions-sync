object HighScores:
  def latest(scores: List[Int]): Int = scores.last

  def personalBest(scores: List[Int]): Int = scores.max

  def personalTop(scores: List[Int]): List[Int] = scores.sorted.reverse.take(3)

  def report(scores: List[Int]): String =
    val last = latest(scores)
    val best = personalBest(scores)
    val short = if last == best then "" else s" ${best - last} short of"

    s"Your latest score was $last. That's$short your personal best!"
