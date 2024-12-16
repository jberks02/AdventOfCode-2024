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
    val incorrectlyPlaced = updates.zipWithIndex.filterNot((value, indx) => checkIfIntIsInCorrectPlace(value, indx, updates)).map(_._1)
    val placedCorrectly = updates.diff(incorrectlyPlaced)
//    println(s"start:$updates\nnotMisplaced: $placedCorrectly\nmisplaced: $incorrectlyPlaced")
    if incorrectlyPlaced.isEmpty then placedCorrectly
    else orderListsCorrectly(orderListsCorrectly(incorrectlyPlaced) ++ placedCorrectly)// orderListsCorrectly(itemsToBePlaced) ++ noMisplacedUpdates
  def countMiddleReportsThatAreOrdered(updates: List[List[Int]], rules: Map[Int, List[Int]]): Int =
    updates.par.map{list =>
      val boolList = list.zipWithIndex.map((value, indx) => checkIfIntIsInCorrectPlace(value, indx, list)(rules))
      if boolList.contains(false) then 0
      else list.drop(list.size / 2).head
    }.sum
  def countOfMiddleInCorrectedReports(updates: List[List[Int]], rules: Map[Int, List[Int]]): Int =
    updates
      .filter(l =>
        !l.zipWithIndex
          .forall((value, indx) => checkIfIntIsInCorrectPlace(value, indx, l)(rules))
      )
      .map{list =>
        val reordered = orderListsCorrectly(list)(rules)
        reordered.drop(list.size/2).head
      }.sum
}
