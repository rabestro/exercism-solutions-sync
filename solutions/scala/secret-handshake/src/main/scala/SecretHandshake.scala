object SecretHandshake:
  private val ReverseMask = 16
  private val ActionMasks = List(
    1 -> "wink",
    2 -> "double blink",
    4 -> "close your eyes",
    8 -> "jump"
  )

  def commands(code: Int): List[String] =
    val actions = ActionMasks.collect:
      case (mask, action) if (mask & code) != 0 => action

    if (ReverseMask & code) != 0 then
      actions.reverse
    else
      actions
