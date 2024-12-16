package Solutions

import scala.collection.parallel.{ParMap, ParSeq}
import scala.collection.parallel.CollectionConverters.*

object DayFiveAnswers {
  private def listContainsAnyOf(left: List[Int], right: List[Int]): Boolean =
    left.exists(right.contains)
  private def checkIfIntIsInCorrectPlace(update: Int, position: Int, updates: List[Int])(implicit rules: Map[Int, List[Int]]): Boolean =
    val allAfters = updates.take(position)
    val listToCheck = rules.getOrElse(update, List())
    !listContainsAnyOf(allAfters, listToCheck)
  private def orderListsCorrectly(updates: List[Int])(implicit rules: Map[Int, List[Int]]): List[Int] =
    val noMisplacedUpdates = updates.zipWithIndex.filterNot((value, indx) => checkIfIntIsInCorrectPlace(value, indx, updates)).map(_._1)
    val itemsToBePlaced = updates.diff(noMisplacedUpdates)
    if itemsToBePlaced.isEmpty then noMisplacedUpdates
    else orderListsCorrectly(itemsToBePlaced) ++ noMisplacedUpdates
  def countMiddleReportsThatAreOrdered(updates: List[List[Int]], rules: Map[Int, List[Int]]): Int =
    updates.par.map{list =>
      val boolList = list.zipWithIndex.map((value, indx) => checkIfIntIsInCorrectPlace(value, indx, list)(rules))
      if boolList.contains(false) then 0
      else list.drop(list.size / 2).head
    }.sum
}
