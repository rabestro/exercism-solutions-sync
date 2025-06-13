import scala.collection.immutable.SortedMap

class School {
  private type DB = Map[Int, Seq[String]]
  private var database: DB = SortedMap.empty

  def add(name: String, g: Int): Unit = {
    database += (g -> (grade(g) :+ name))
  }

  def db: DB = database

  def grade(g: Int): Seq[String] = {
    db.getOrElse(g, Seq.empty)
  }

  def sorted: DB = database.view.mapValues(_.sorted).toMap
}
