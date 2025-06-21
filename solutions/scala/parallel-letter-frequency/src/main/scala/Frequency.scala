import scala.concurrent.{Future, Await, ExecutionContext}
import scala.concurrent.duration._
import java.util.concurrent.Executors

object Frequency {
  def frequency(numWorkers: Int, texts: Seq[String]): Map[Char, Int] = {
    // Create a custom execution context with a fixed thread pool
    implicit val ec: ExecutionContext = ExecutionContext
      .fromExecutorService(Executors.newFixedThreadPool(numWorkers))

    // Split the texts into chunks for parallel processing
    val textChunks = texts.grouped(math.max(1, texts.length / numWorkers)).toSeq

    // Process each chunk in parallel
    val futures = textChunks.map { chunk =>
      Future {
        chunk
          .flatMap(_.toSeq)
          .filter(_.isLetter)
          .map(_.toLower)
          .groupBy(identity)
          .view
          .mapValues(_.size)
      }
    }

    // Await the results and combine them
    val results = Await.result(Future.sequence(futures), 10.seconds)
    results.flatten.groupBy(_._1).view.mapValues(_.map(_._2).sum).toMap
  }
}
