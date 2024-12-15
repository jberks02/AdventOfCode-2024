package Solutions

import scala.collection.parallel.CollectionConverters.*
import scala.math.*

object DayOneAnswers {
  def processLeftRightList(left: List[Int], right: List[Int]): Int =
    val lOrdered = left.sortWith(_ > _)
    val rOrdered = right.sortWith(_ > _)
    val zipped = lOrdered.zip(rOrdered).map { case (a, b) => abs(a - b) }.par
    zipped.sum
  def multiplicativeLeftRightListProcessing(left: List[Int], right: List[Int]): Int =
    val rParred = right.par
    left.par.map(lNum => lNum * rParred.count(_ == lNum)).sum
}
