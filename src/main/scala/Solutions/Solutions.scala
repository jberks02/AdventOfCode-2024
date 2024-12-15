package Solutions

import scala.collection.parallel.CollectionConverters.*
import scala.math.*
import scala.util.Try
import scala.util.matching.Regex




private def multiplyMulItems(mul: String): Int =
  val removeChars: List[Char] = List('m', 'u', 'l', '(', ')')
  mul
    .filterNot(c => removeChars.contains(c))
    .split(',')
    .map(_.toInt)
    .product
def fixCorruptedInst(instructions: String): Int =
  val pattern: Regex = """mul\((\d+),(\d+)\)""".r
  pattern.findAllIn(instructions).toList.par.map(multiplyMulItems).sum

def fixCorruptedInstWithDoDont(instructions: String): Int =
  val pattern: Regex = """(mul\(\d+,\d+\)|do\(\)|don't\(\))""".r
  pattern
    .findAllIn(instructions).toList
    .foldLeft[(Int, Boolean)](0, true){(foldVars, subString) =>
      if subString == "do()" then (foldVars._1, true)
      else if subString == "don't()" then (foldVars._1, false)
      else if subString.contains("mul(") && foldVars._2 then (foldVars._1 + multiplyMulItems(subString), foldVars._2)
      else foldVars
    }._1

private def createIndexedSeq(grid: List[List[Char]], op: (Int, Int) => Int): IndexedSeq[(Int, Char)] =
  for {
    r <- grid.indices
    c <- grid(r).indices
  } yield (op(r,c), grid(r)(c))

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
  subStrings.map{sub =>
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
  str.zipWithIndex.map{
    case (c, indx) =>
      if(c != 'A' || indx == 0 || indx == str.length - 1) then 0
      else {
        val opOne = sourceSet(strLevel - 1)(indx - 1).toString + c.toString + sourceSet(strLevel + 1)(indx + 1).toString
        val opTwo = sourceSet(strLevel + 1)(indx - 1).toString + c.toString + sourceSet(strLevel - 1)(indx + 1).toString
        if checkIfStringIsMas(opOne) && checkIfStringIsMas(opTwo) then 1 else 0
      }
  }.par.sum

def searchDiagonals(wordSearch: String, subString: String): Int =
  val horizontal = wordSearch.linesIterator.toList
  horizontal.zipWithIndex.par.map{
    case (str, indx) =>
      if indx == 0 || indx == horizontal.length -1 then 0
      else findXmasInstanceOnLine(str, horizontal.toArray, indx)
  }.sum

//Day Five
private def listContainsAnyOf(left: List[Int], right: List[Int]): Boolean =
  left.exists(right.contains)
private def checkIfIntIsInCorrectPlace(update: Int, position: Int, updates: List[Int])(implicit rules: Map[Int, IndexedSeq[Int]]): Boolean =
  val allAfters = updates.drop(position)
  val listToCheck = rules(update).toList
  listContainsAnyOf(allAfters, listToCheck)
private def orderListsCorrectly(updates: List[Int])(implicit rules: Map[Int, IndexedSeq[Int]]): List[Int] = ???
//  val noMisplacedUpdates = updates.zipWithIndex.filterNot((value, indx) => checkIfIntIsInCorrectPlace(value, indx, updates))
//  val itemsToBePlaced = 

def checkIfUpdatesAreInOrder(updates: List[List[Int]], rules: Map[Int, IndexedSeq[Int]]): Int = ???
