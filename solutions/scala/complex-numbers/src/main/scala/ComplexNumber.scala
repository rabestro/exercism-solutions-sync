final case class ComplexNumber(real: Double = 0, imaginary: Double = 0):
  def +(that: ComplexNumber): ComplexNumber =
    ComplexNumber(real + that.real, imaginary + that.imaginary)

  def -(that: ComplexNumber): ComplexNumber =
    ComplexNumber(real - that.real, imaginary - that.imaginary)

  def *(that: ComplexNumber): ComplexNumber =
    ComplexNumber(real * that.real - imaginary * that.imaginary, real * that.imaginary + imaginary * that.real)

  def /(that: ComplexNumber): ComplexNumber =
    ComplexNumber(
      (real * that.real + imaginary * that.imaginary) / (that.real * that.real + that.imaginary * that.imaginary),
      (imaginary * that.real - real * that.imaginary) / (that.real * that.real + that.imaginary * that.imaginary)
    )

  def abs: Double =
    math.sqrt(real * real + imaginary * imaginary)

  def conjugate: ComplexNumber =
    ComplexNumber(real, -imaginary)


object ComplexNumber:
  def exp(z: ComplexNumber): ComplexNumber =
    ComplexNumber(math.cos(z.imaginary), math.sin(z.imaginary)) * ComplexNumber(math.exp(z.real))

