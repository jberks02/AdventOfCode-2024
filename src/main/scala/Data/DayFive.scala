package Data

import scala.io.Source
import scala.util.Using
import scala.collection.parallel.CollectionConverters.*
import scala.collection.parallel.{ParMap, ParSeq}

object DayFive {
  private val path: String = "/Users/justinberkshire/adventOfCode/adventsnips/src/main/scala/Data/DayFive.txt"
  private val textInput: String = Using.resource(Source.fromFile(path))(_.mkString)
  private def parseOutOrderingMap(blockText: String):  ParMap[Int, ParSeq[Int]] = {
    blockText
      .split("\n").toList.par
      .filter(_.contains("|"))
      .map { pair =>
        val splitted = pair.split("|")
        (splitted.head.toInt, splitted.tail.head.toInt)
      }.groupBy(_._1).map((key, list) => (key, list.map(_._2)))
  }
  private def getUpdateList(blockText: String): List[List[Int]] = {
    blockText
      .split("\n").toList.par
      .filterNot(_.contains("|"))
      .map(_.split(" ").map(_.toInt).toList).toList
  }
  val dayFiveOne = (parseOutOrderingMap(textInput), getUpdateList(textInput))
}
