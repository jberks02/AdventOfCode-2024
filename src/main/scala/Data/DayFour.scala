package Data

import scala.io.Source
import scala.util.Using


object DayFour {
  private val path: String = "/Users/justinberkshire/adventOfCode/AdventOfCode-2024/src/main/scala/Data/DayFour.txt"
  private val partTwoPath: String = "/Users/justinberkshire/adventOfCode/AdventOfCode-2024/src/main/scala/Data/DayFour.2.txt"
  val wordSearch: String = Using.resource(Source.fromFile(path))(_.mkString)
  val xmasWordSearch: String = Using.resource(Source.fromFile(partTwoPath))(_.mkString)
}
