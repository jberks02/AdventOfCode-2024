package Solutions

import scala.collection.parallel.CollectionConverters.*
import scala.math.*

object DayTwoAnswers {
  private def checkIfOrdered(report: List[Int]): Boolean =
    //true = up down = false
    if (report.toSet.size != report.length) then false
    else
      val slidList = report.sliding(2, 1).map {
        case List(a, b) =>
          if a < b then (true, abs(a - b))
          else (false, abs(a - b))
        case _ => (true, 0)
      }.toList
      slidList.find(_._2 > 3) match
        case Some(value) => false
        case None =>
          val bools = slidList.map(_._1)
          !(bools.contains(false) && bools.contains(true))

  def checkIfReportsAreSafe(levelReports: List[List[Int]]): Int =
    levelReports.par.map(checkIfOrdered).count(_ == true)

  private def createSubListSetWithMissingValue(list: List[Int]): List[List[Int]] =
    list.zipWithIndex.map {
      case (_, indx) => list.take(indx) ++ list.drop(indx + 1)
    }

  def checkLevelsWithDampener(levelReports: List[List[Int]]): Int =
    levelReports.par.map { level =>
      if checkIfOrdered(level) then true
      else {
        createSubListSetWithMissingValue(level).map { levelList =>
          val check = checkIfOrdered(levelList)
          check
        }.count(_ == true) >= 1
      }
    }.count(_ == true)
}
