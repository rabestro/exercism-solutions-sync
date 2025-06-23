object Strain {
  def keep[A](data: Seq[A], fun: A => Boolean): Seq[A] =
    data.filter(fun)
    
  def discard[A](data: Seq[A], fun: A => Boolean): Seq[A] =
    data.filterNot(fun)
}
  
