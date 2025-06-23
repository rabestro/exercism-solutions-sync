module ComplexNumbers

open System.Numerics

let create real imaginary = Complex(real, imaginary)

let mul (z1: Complex) (z2: Complex) = Complex.Multiply(z1, z2)

let add (z1: Complex) (z2: Complex) = Complex.Add(z1, z2)

let sub (z1: Complex) (z2: Complex) = Complex.Subtract(z1, z2)

let div (z1: Complex) (z2: Complex) = Complex.Divide(z1, z2)

let abs (z: Complex) = Complex.Abs z

let conjugate (z: Complex) = Complex.Conjugate z

let real (z: Complex) = z.Real

let imaginary (z: Complex) = z.Imaginary

let exp (z: Complex) = Complex.Exp z
