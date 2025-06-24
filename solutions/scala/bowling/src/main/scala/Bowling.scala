import Bowling.{MaxFrames, MaxPins}

sealed trait Bowling:
  def roll(pins: Int): Bowling

  def score(): Either[String, Int]

object Bowling:
  val MinPins = 0
  val MaxPins = 10
  val MaxFrames = 10

  def apply(): Bowling = RunningGame(Nil, EmptyFrame)

  def withPinValidation(pins: Int)(logic: Int => Bowling): Bowling =
    if (pins < MinPins || pins > MaxPins)
      IncorrectGame(s"Pins must have a value from $MinPins to $MaxPins")
    else
      logic(pins)

sealed trait IncompleteGame extends Bowling:
  override def score(): Either[String, Int] =
    Left("Score cannot be taken until the end of the game")

case class RunningGame(completed: List[CompletedFrame], current: StateFrame = EmptyFrame) extends IncompleteGame:
  override def roll(pins: Int): Bowling = Bowling.withPinValidation(pins) { pins =>
    current match
      case IncompleteFrame(first) if first + pins > MaxPins =>
        IncorrectGame("Pin count exceeds pins on the lane")

      case IncompleteFrame(first) if first + pins == MaxPins =>
        val frames = SpareFrame(first, pins) :: completed
        frames.length match
          case MaxFrames => AwaitingSpareBonus(frames)
          case _ => RunningGame(frames)

      case IncompleteFrame(first) =>
        val frames = OpenFrame(first, pins) :: completed
        frames.length match
          case MaxFrames => CompletedGame(frames)
          case _ => RunningGame(frames)

      case EmptyFrame if pins == 10 =>
        val frames = StrikeFrame :: completed
        frames.length match
          case MaxFrames => AwaitingStrikeBonus(frames)
          case _ => RunningGame(frames)

      case EmptyFrame =>
        RunningGame(completed, IncompleteFrame(pins))
  }

case class AwaitingSpareBonus(frames: List[CompletedFrame]) extends IncompleteGame:
  override def roll(pins: Int): Bowling = Bowling.withPinValidation(pins) { pins =>
    CompletedGame(frames, pins :: Nil)
  }

case class AwaitingStrikeBonus(frames: List[CompletedFrame], rolls: List[Int] = Nil) extends IncompleteGame:
  override def roll(pins: Int): Bowling = Bowling.withPinValidation(pins) { pins =>
    rolls match
      case first :: Nil if first < MaxPins && first + pins > MaxPins =>
        IncorrectGame(s"Second bonus roll after a strike in the last frame cannot score more than $MaxPins points")
      case first :: Nil =>
        CompletedGame(frames, first :: pins :: Nil)
      case _ =>
        AwaitingStrikeBonus(frames, pins :: Nil)
  }

case class IncorrectGame(reason: String) extends Bowling:
  override def roll(pins: Int): Bowling = this

  override def score(): Either[String, Int] = Left(reason)

case class CompletedGame(frames: List[CompletedFrame], rolls: List[Int] = Nil) extends Bowling:
  override def roll(pins: Int): Bowling =
    IncorrectGame("Should not be able to roll after game is over")

  override def score(): Either[String, Int] =
    Right(score(frames, rolls))

  private def score(frames: List[CompletedFrame], rolls: List[Int]): Int =
    frames match
      case Nil => 0
      case StrikeFrame :: tail => MaxPins + rolls.head + rolls.tail.head + score(tail, MaxPins :: rolls)
      case SpareFrame(first, second) :: tail => MaxPins + rolls.head + score(tail, first :: second :: rolls)
      case OpenFrame(first, second) :: tail => first + second + score(tail, first :: second :: rolls)
