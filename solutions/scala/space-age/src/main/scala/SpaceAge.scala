object SpaceAge:
  private val earthYearInSeconds = 31_557_600.0

  private def calculateOrbitalAge(orbitPeriod: Double) =
    (seconds: Double) => seconds / earthYearInSeconds / orbitPeriod

  val onEarth: Double => Double = calculateOrbitalAge(1.0)
  val onMercury: Double => Double = calculateOrbitalAge(0.2408467)
  val onVenus: Double => Double = calculateOrbitalAge(0.61519726)
  val onMars: Double => Double = calculateOrbitalAge(1.8808158)
  val onJupiter: Double => Double = calculateOrbitalAge(11.862615)
  val onSaturn: Double => Double = calculateOrbitalAge(29.447498)
  val onUranus: Double => Double = calculateOrbitalAge(84.016846)
  val onNeptune: Double => Double = calculateOrbitalAge(164.79132)
