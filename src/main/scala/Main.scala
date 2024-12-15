import Data.DayOne.*
import Solutions.DayOneAnswers.*
import Data.DayThree.*
import Data.DayTwo.*
import Solutions.DayTwoAnswers.*
import Data.DayFour.*
import Solutions.*

@main def main(): Unit =
  val summedDiffs = processLeftRightList(leftList, rightList)
  println(s"summedDiffs: $summedDiffs")
  val multipliedLeftRight = multiplicativeLeftRightListProcessing(leftPartTwo, rightPartTwo)
  println(s"multiplied: $multipliedLeftRight")
  val levelReport = checkIfReportsAreSafe(reportOne)
  println(s"Clear Levels: $levelReport")
  val levelReportWithDampen = checkLevelsWithDampener(reportTwo)
  println(s"Clear Dampened: $levelReportWithDampen")
  val corruptedDataFix = fixCorruptedInst(corrupted)
  println(s"corrupt data result: $corruptedDataFix")
  val corruptDoDontFix = fixCorruptedInstWithDoDont(corruptedWithDoDont)
  println(s"corrupted 2 data result: $corruptDoDontFix")
  val wordSearchSolution = searchForString(wordSearch, "XMAS")
  println(s"XMAS count: $wordSearchSolution")
  val xmasWordSearchAttempt = searchDiagonals(xmasWordSearch, "MAS")
  println(s"X-MAS count: $xmasWordSearchAttempt")

