package Data

import scala.io.Source
import scala.util.Using

object DaySix {
  private val testPath = "/Users/justinberkshire/adventOfCode/AdventOfCode-2024/src/main/scala/Data/DaySix.test.txt"
  private val path = "/Users/justinberkshire/adventOfCode/AdventOfCode-2024/src/main/scala/Data/DaySix.txt"
  private val pathTwo = "/Users/justinberkshire/adventOfCode/AdventOfCode-2024/src/main/scala/Data/DaySix.2.txt"
  private val testInputText = Using.resource(Source.fromFile(testPath))(_.mkString)
  private val inputOneText = Using.resource(Source.fromFile(path))(_.mkString)
  private val inputTwoText = Using.resource(Source.fromFile(pathTwo))(_.mkString)
  val testInput: List[List[Char]] = testInputText.split("\n").toList.map(_.toCharArray.toList)
  val inputOne: List[List[Char]] = inputOneText.split("\n").toList.map(_.toCharArray.toList)
  val testTwo: List[List[Char]] = inputTwoText.split("\n").toList.map(_.toCharArray.toList)
}
