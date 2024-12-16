package Solutions

object DayFiveAnswers {
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

}
