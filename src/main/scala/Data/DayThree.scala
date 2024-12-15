package Data

import scala.io.Source
import scala.util.Using

object DayThree {
  private val path = "/Users/justinberkshire/adventOfCode/adventsnips/src/main/scala/Data/DayThree.txt"
  private val path2 = "/Users/justinberkshire/adventOfCode/adventsnips/src/main/scala/Data/DayThree.txt"
  val corrupted: String = Using.resource(Source.fromFile(path))(_.mkString)
  val corruptedWithDoDont: String = Using.resource(Source.fromFile(path2))(_.mkString)
}