package Data

import scala.io.Source
import scala.util.Using

object DayOne {
  val path = "/Users/justinberkshire/adventOfCode/AdventOfCode-2024/src/main/scala/Data/DayOne.txt"
  val pathForPartTwo = "/Users/justinberkshire/adventOfCode/AdventOfCode-2024/src/main/scala/Data/DayOne.2.txt"
  val fileContents: String = Using.resource(Source.fromFile(path))(_.mkString)
  val fileContentsTwo =  Using.resource(Source.fromFile(pathForPartTwo))(_.mkString)
  val leftList: List[Int] = fileContents.split("\n").map(_.split("   ").head.toInt).toList
  val rightList: List[Int] = fileContents.split("\n").map(_.split("   ").tail.head.toInt).toList
  val leftPartTwo: List[Int] = fileContentsTwo.split("\n").map(_.split("   ").head.toInt).toList
  val rightPartTwo: List[Int] = fileContentsTwo.split("\n").map(_.split("   ").tail.head.toInt).toList
}