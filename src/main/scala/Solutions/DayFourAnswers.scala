package Solutions

import scala.collection.parallel.CollectionConverters.*

object DayFourAnswers {
  private def createIndexedSeq(grid: List[List[Char]], op: (Int, Int) => Int): IndexedSeq[(Int, Char)] =
    for {
      r <- grid.indices
      c <- grid(r).indices
    } yield (op(r, c), grid(r)(c))

  private def transformToLayeredMap(ind: IndexedSeq[(Int, Char)]): List[List[Char]] =
    ind.groupBy(_._1).toList.sortBy(_._1).map(_._2.map(_._2).toList)

  private def createLeftRightDiagonals(text: String): (List[List[Char]], List[List[Char]]) =
    val grid = text.linesIterator.map(_.toList).toList
    val leftToRightIndexed = createIndexedSeq(grid, (r, c) => r + c)
    val rightToLeftIndexed = createIndexedSeq(grid, (r, c) => r - c)
    val lr = transformToLayeredMap(leftToRightIndexed)
    val rl = transformToLayeredMap(rightToLeftIndexed)
    (lr, rl)

  private def countOfSubsInString(lines: List[String], subStrings: List[String]): Int =
    subStrings.map { sub =>
      lines.par.map(_.sliding(sub.length).count(w => w == sub)).sum
    }.sum


  def searchForString(wordSearch: String, subString: String): Int =
    val horizontal: List[String] = wordSearch.linesIterator.toList
    val vertical: List[String] = wordSearch.linesIterator.toList.transpose.map(_.mkString)
    val (leftToRight, rightToLeft) = createLeftRightDiagonals(wordSearch)
    val allSubStrates: List[List[String]] = List(
      horizontal,
      vertical.map(_.mkString),
      leftToRight.map(_.mkString),
      rightToLeft.map(_.mkString)
    )
    allSubStrates.map(countOfSubsInString(_, List(subString, subString.reverse))).sum

  def checkIfStringIsMas(mas: String): Boolean = mas.equals("MAS") || mas.reverse.equals("MAS")

  def findXmasInstanceOnLine(str: String, sourceSet: Array[String], strLevel: Int): Int =
    val upOne = strLevel - 1
    val downOne = strLevel + 1
    str.zipWithIndex.map {
      case (c, indx) =>
        if (c != 'A' || indx == 0 || indx == str.length - 1) then 0
        else {
          val opOne = sourceSet(strLevel - 1)(indx - 1).toString + c.toString + sourceSet(strLevel + 1)(indx + 1).toString
          val opTwo = sourceSet(strLevel + 1)(indx - 1).toString + c.toString + sourceSet(strLevel - 1)(indx + 1).toString
          if checkIfStringIsMas(opOne) && checkIfStringIsMas(opTwo) then 1 else 0
        }
    }.par.sum

  def searchDiagonals(wordSearch: String, subString: String): Int =
    val horizontal = wordSearch.linesIterator.toList
    horizontal.zipWithIndex.par.map {
      case (str, indx) =>
        if indx == 0 || indx == horizontal.length - 1 then 0
        else findXmasInstanceOnLine(str, horizontal.toArray, indx)
    }.sum

}
