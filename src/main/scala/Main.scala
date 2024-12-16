import Solutions.DayOneAnswers.*
import Solutions.DayTwoAnswers.*
import Solutions.DayThreeAnswers.*
import Solutions.DayFourAnswers.*
import Solutions.DayFiveAnswers.*
import Data.DayOne.*
import Data.DayTwo.*
import Data.DayThree.*
import Data.DayFour.*
import Data.DayFive.*

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
  val correctReports = countMiddleReportsThatAreOrdered(dayFiveOne._2, dayFiveOne._1)
  println(s"Counted middles: $correctReports")


