package Data

import scala.io.Source
import scala.util.Using
import scala.collection.parallel.CollectionConverters.*
import scala.collection.parallel.{ParMap, ParSeq}

object DayFive {
  private val testPath: String = "/Users/justinberkshire/adventOfCode/AdventOfCode-2024/src/main/scala/Data/DayFive.test.txt"
  private val fiveOnePath: String = "/Users/justinberkshire/adventOfCode/AdventOfCode-2024/src/main/scala/Data/DayFive.txt"
  private val fiveTwoPath: String = "/Users/justinberkshire/adventOfCode/AdventOfCode-2024/src/main/scala/Data/DayFive.2.txt"
  private val testInput: String = Using.resource(Source.fromFile(testPath))(_.mkString)
  private val fiveOneInput: String = Using.resource(Source.fromFile(fiveOnePath))(_.mkString)
  private val fiveTwoInput: String = Using.resource(Source.fromFile(fiveTwoPath))(_.mkString)
  private def parseOutOrderingMap(blockText: String):  Map[Int, List[Int]] = {
    blockText
      .split("\n").toList
      .filter(_.contains("|"))
      .map { pair =>
        val splitted = pair.split("\\|")
        (splitted.head.toInt, splitted.tail.head.toInt)
      }.groupBy(_._1)
      .map((key, list) => (key, list.map(_._2)))
  }
  private def getUpdateList(blockText: String): List[List[Int]] = {
    blockText
      .split("\n")
      .filter(_.contains(","))
      .map(_.split(",").map(_.toInt).toList)
      .toList
  }
  val dayFiveTest: (Map[Int, List[Int]], List[List[Int]]) = (parseOutOrderingMap(testInput), getUpdateList(testInput))
  val dayFiveOne: (Map[Int, List[Int]], List[List[Int]]) = (parseOutOrderingMap(fiveOneInput), getUpdateList(fiveOneInput))
  val dayFiveTwo: (Map[Int, List[Int]], List[List[Int]]) = (parseOutOrderingMap(fiveTwoInput), getUpdateList(fiveTwoInput))
}
