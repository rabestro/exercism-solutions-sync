enum Sublist:
  case Equal, Unequal, Sublist, Superlist

object Sublist:
  def sublist(a: List[Int], b: List[Int]): Sublist =
    if a == b then Equal
    else if a containsSlice b then Superlist
    else if b containsSlice a then Sublist
    else Unequal
