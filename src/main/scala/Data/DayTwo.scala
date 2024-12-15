package Data

import scala.io.Source
import scala.util.Using

object DayTwo {
  val path = "/Users/justinberkshire/adventOfCode/adventsnips/src/main/scala/Data/DayTwo.txt"
  val partTwo = "/Users/justinberkshire/adventOfCode/adventsnips/src/main/scala/Data/DayTwo.txt"
  val fileContents: String = Using.resource(Source.fromFile(path))(_.mkString)
  val partTwoFile: String = Using.resource(Source.fromFile(partTwo))(_.mkString)
  val reportOne: List[List[Int]] = fileContents.split("\n").map(_.split(" ").map(_.toInt).toList).toList
  val reportTwo: List[List[Int]] = partTwoFile.split("\n").map(_.split(" ").map(_.toInt).toList).toList
}
