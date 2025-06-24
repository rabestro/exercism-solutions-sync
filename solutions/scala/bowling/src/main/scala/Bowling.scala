sealed trait Bowling:
  val MaxFrames = 10
  val MinPins = 0
  val MaxPins = 10

  def roll(pins: Int): Bowling

  def score(): Either[String, Int]

object Bowling:
  def apply(): Bowling = RunningGame(Nil, EmptyFrame)

sealed trait IncompleteGame extends Bowling:
  override def score(): Either[String, Int] =
    Left("Score cannot be taken until the end of the game")

case class RunningGame(completed: List[CompletedFrame], current: StateFrame = EmptyFrame) extends IncompleteGame:
  override def roll(pins: Int): Bowling =
    if pins < MinPins || pins > MaxPins then
      IncorrectGame("Pins must have a value from 0 to 10")
    else current match
      case IncompleteFrame(first) if first + pins > MaxPins =>
        IncorrectGame("Pin count exceeds pins on the lane")

      case IncompleteFrame(first) if first + pins == MaxPins =>
        val frames = SpareFrame(first, pins) :: completed
        frames.length match
          case MaxFrames => BonusGame(frames)
          case _ => RunningGame(frames)

      case IncompleteFrame(first) =>
        val frames = OpenFrame(first, pins) :: completed
        frames.length match
          case MaxFrames => CompletedGame(frames)
          case _ => RunningGame(frames)

      case EmptyFrame if pins == 10 =>
        val frames = StrikeFrame :: completed
        frames.length match
          case MaxFrames => BonusGame(frames)
          case _ => RunningGame(frames)

      case EmptyFrame =>
        RunningGame(completed, IncompleteFrame(pins))

case class BonusGame(completed: List[CompletedFrame], rolls: List[Int] = Nil) extends IncompleteGame:
  override def roll(pins: Int): Bowling =
    if pins < MinPins || pins > MaxPins then
      IncorrectGame("Pins must have a value from 0 to 10")
    else
      rolls match
        case Nil if completed.head.isInstanceOf[SpareFrame] =>
          CompletedGame(completed, List(pins))
        case first :: Nil if first != 10 && first + pins > MaxPins =>
          IncorrectGame("Pin count exceeds pins on the lane")
        case first :: Nil =>
          CompletedGame(completed, first :: pins :: Nil)
        case _ =>
          BonusGame(completed, pins :: Nil)

case class IncorrectGame(reason: String) extends Bowling:
  override def roll(pins: Int): Bowling = this

  override def score(): Either[String, Int] = Left(reason)

case class CompletedGame(frames: List[CompletedFrame], rolls: List[Int] = Nil) extends Bowling:
  override def roll(pins: Int): Bowling =
    IncorrectGame("Should not be able to roll after game is over")

  private def scoreFrame(frames: List[CompletedFrame], rolls: List[Int]): Int =
    frames match
      case Nil => 0
      case StrikeFrame :: tail => MaxPins + rolls.head + rolls.tail.head + scoreFrame(tail, MaxPins :: rolls)
      case SpareFrame(a, b) :: tail => MaxPins + rolls.head + scoreFrame(tail, a :: b :: rolls)
      case OpenFrame(a, b) :: tail => a + b + scoreFrame(tail, a :: b :: rolls)

  override def score(): Either[String, Int] =
    Right(scoreFrame(frames, rolls))

