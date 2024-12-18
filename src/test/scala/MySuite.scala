import Data.DayFive.*
import Data.DaySix.testInput
import Solutions.DayOneAnswers.*
import Solutions.DayTwoAnswers.*
import Solutions.DayThreeAnswers.*
import Solutions.DayFourAnswers.*
import Solutions.DayFiveAnswers.*
import Solutions.DaySixAnswers.*


class MySuite extends munit.FunSuite {
  test("Day One: 1") {
    val l = List(3, 4, 2, 1, 3, 3)
    val r = List(4, 3, 5, 3, 9, 3)
    val res = processLeftRightList(l, r)
    assertEquals(res, 11)
  }
  test("Day One: 2") {
    val l = List(3,4,2,1,3,3)
    val r = List(4,3,5,3,9,3)
    val res = multiplicativeLeftRightListProcessing(l, r)
    assertEquals(res, 31)
  }
  test("Day two: 1") {
    val spread = List(
      List(7, 6, 4, 2, 1),
      List(1, 2, 7, 8, 9),
      List(9, 7, 6, 2, 1),
      List(1, 3, 2, 4, 5),
      List(8, 6, 4, 4, 1),
      List(1, 3, 6, 7, 9)
    )
    val res = checkIfReportsAreSafe(spread)
    assertEquals(res, 2)
  }
//  test("Creates List of uniques") {
//    val l = List(1,2,3,4)
//    val lOfL = createSubListSetWithMissingValue(l)
//    println(lOfL)
//    assertEquals(lOfL, List(List(2, 3, 4), List(1, 3, 4), List(1, 2, 4), List(1, 2, 3)))
//  }
  test("Day two: 2") {
    val spread = List(
      List(7, 6, 4, 2, 1),
      List(1, 2, 7, 8, 9),
      List(9, 7, 6, 2, 1),
      List(1, 3, 2, 4, 5),
      List(8, 6, 4, 4, 1),
      List(1, 3, 6, 7, 9)
    )
    val res = checkLevelsWithDampener(spread)
    assertEquals(res, 4)
  }
  test("Day three: 1") {
    val corrupt = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    assertEquals(fixCorruptedInst(corrupt), 161)
  }
  test("Day three: 2") {
    val corrupt = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
    assertEquals(fixCorruptedInstWithDoDont(corrupt), 48)
  }
  test("Day Four: 1") {
    val wordSearch =
      """|MMMSXXMASM
        |MSAMXMSMSA
        |AMXSXMAAMM
        |MSAMASMSMX
        |XMASAMXAMM
        |XXAMMXXAMA
        |SMSMSASXSS
        |SAXAMASAAA
        |MAMMMXMMMM
        |MXMXAXMASX""".stripMargin
    assertEquals(searchForString(wordSearch, "XMAS"), 18)
  }
  test("Day Four: 2") {
    val wordSearch =
      """.M.S......
        |..A..MSMS.
        |.M.S.MAA..
        |..A.ASMSM.
        |.M.S.M....
        |..........
        |S.S.S.S.S.
        |.A.A.A.A..
        |M.M.M.M.M.
        |..........""".stripMargin
    assertEquals(searchDiagonals(wordSearch, "MAS"), 9)
  }
  test("Day Five: 1") {
    val middleAdded = countMiddleReportsThatAreOrdered(dayFiveTest._2, dayFiveTest._1)
    assertEquals(middleAdded, 143)
  }
  test("Day Five: 2") {
    val wrongMiddleFix = countOfMiddleInCorrectedReports(dayFiveTest._2, dayFiveTest._1)
    assertEquals(wrongMiddleFix, 123)
  }
  test("Day Six: 1") {
    val (count, guard, map) = trackFacilityGuardPositions(testInput)
    assertEquals(count, 41)
  }
  test("Day Six: 2") {
    val numWays = findObstructionCirclePaths(testInput)
    assertEquals(numWays, 6)
  }
}
