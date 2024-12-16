package Solutions

import scala.util.matching.Regex
import scala.collection.parallel.CollectionConverters.*

object DayThreeAnswers {
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
      .foldLeft[(Int, Boolean)](0, true) { (foldVars, subString) =>
        if subString == "do()" then (foldVars._1, true)
        else if subString == "don't()" then (foldVars._1, false)
        else if subString.contains("mul(") && foldVars._2 then (foldVars._1 + multiplyMulItems(subString), foldVars._2)
        else foldVars
      }._1
}
