import Category.Person

object ZebraPuzzle {

  sealed trait Resident

  case object Englishman extends Resident

  case object Spaniard extends Resident

  case object Ukrainian extends Resident

  case object Norwegian extends Resident

  case object Japanese extends Resident

  case class Solution(waterDrinker: Resident, zebraOwner: Resident)

  lazy val solve: Solution =
    val facts = Domain.solve.get

    def findPerson(personHouse: House): Resident =
      facts.find((subject, house) => house == personHouse && Domain.Subjects(subject) == Person)
        .map { (subject, _) =>
          subject match {
            case "Englishman" => Englishman
            case "Spaniard" => Spaniard
            case "Ukrainian" => Ukrainian
            case "Norwegian" => Norwegian
            case "Japanese" => Japanese
          }
        }.get

    val waterDrinker = findPerson(facts("water"))
    val zebraOwner = findPerson(facts("zebra"))

    Solution(waterDrinker, zebraOwner)

}  