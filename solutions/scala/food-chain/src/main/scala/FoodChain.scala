import java.util.StringJoiner

object FoodChain:
  private val Phrase = List(
    "fly" -> "I don't know why she swallowed the fly. Perhaps she'll die.",
    "spider" -> "It wriggled and jiggled and tickled inside her.",
    "bird" -> "How absurd to swallow a bird!",
    "cat" -> "Imagine that, to swallow a cat!",
    "dog" -> "What a hog, to swallow a dog!",
    "goat" -> "Just opened her throat and swallowed a goat!",
    "cow" -> "I don't know how she swallowed a cow!",
    "horse" -> "She's dead, of course!"
  )

  def recite(start: Int, end: Int): String =
    (start - 1 until end).map(verse).mkString("", "\n\n", "\n\n")

  private def verse(i: Int): String =
    val result = StringJoiner("\n")
    val animal = Phrase(i)._1
    result.add(s"I know an old lady who swallowed a $animal.")
    if i > 0 then result.add(Phrase(i)._2)
    if i < Phrase.size - 1 then
      (i to 1 by -1).foreach { x =>
        val predator  = Phrase(x)._1
        val prey = Phrase(x - 1)._1
        val description = if x == 2 then " that wriggled and jiggled and tickled inside her" else ""
        result.add(s"She swallowed the $predator to catch the $prey$description.")
      }
      result.add(Phrase.head._2)
    result.toString