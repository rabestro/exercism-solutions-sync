object Strain {
  def keep[A](data: List[A], fun: A => Boolean): List[A] =
    data.filter(fun)
    
  def discard[A](data: List[A], fun: A => Boolean): List[A] =
    data.filterNot(fun)
}
  
