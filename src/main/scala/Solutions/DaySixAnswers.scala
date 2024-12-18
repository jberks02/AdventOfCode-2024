package Solutions

import scala.annotation.tailrec
import scala.collection.parallel.CollectionConverters.*
import scala.collection.parallel.ParSeq

object DaySixAnswers {
  private val validCharacters: List[Char] = List('v', '>', '<', '^')
  case class Guard(
                    position: (Int, Int),
                    direction: Int, //0-3
                    guardChar: Char
                  )
  private def lineContainsOneOf(line: List[Char], items: List[Char]): Boolean = validCharacters.exists(c => line.contains(c))
  private def searchForGuard(map: List[List[Char]]): (Int, Int) =
    val x = map.indexWhere(line => lineContainsOneOf(line, validCharacters))
    val y = map(x).indexWhere(c => validCharacters.contains(c))
    (x, y)
  private def getGuardDirection(map: List[List[Char]]): Int =
    val pos = searchForGuard(map)
    val guardChar = map(pos._1)(pos._2)
    if guardChar == '^' then 0
    else if guardChar == '>' then 1
    else if guardChar == 'v' then 2
    else 3 //>
  private def getCharForDirection(dir: Int): Char =
    if dir == 0 then '^'
    else if dir == 1 then '>'
    else if dir == 2 then 'v'
    else if dir == 3 then '<'
    else throw Exception("Invalid direction sent to get char.")
  private def rotateGuard90Degrees(guard: Guard): Guard =
    if guard.direction < 3 then guard.copy(direction = guard.direction + 1)
    else guard.copy(direction = 0)
  private def getNextPos(guard: Guard): (Int, Int) =
    if guard.direction == 0 then (guard.position._1 - 1, guard.position._2)
    else if guard.direction == 1 then (guard.position._1, guard.position._2 + 1)
    else if guard.direction == 2 then (guard.position._1 + 1, guard.position._2)
    else if guard.direction == 3 then (guard.position._1, guard.position._2 - 1)
    else throw Exception("An undefined direction was set")
  private def validatePositionIsInBounds(pos: (Int, Int), map: List[List[Char]]): Boolean =
    map.length > pos._1 && pos._1 >= 0 &&
      pos._2 >= 0 && map(pos._1).length > pos._2
  private def positionIsValid(pos: (Int, Int), map: List[List[Char]]): Boolean =
    if(map(pos._1)(pos._2) == '#') then false
    else true
  private def updateMapWithNewCharacter(pos: (Int, Int), direction: Int, map: List[List[Char]]): List[List[Char]] =
    val charToAdd: Char = getCharForDirection(direction)
    map.zipWithIndex.map{(charList, indx) =>
      if indx == pos._1 then charList.zipWithIndex.map((c, indx) => if indx == pos._2 then charToAdd else c)
      else charList
    }
  @tailrec
  private def moveGuard(guard: Guard, map: List[List[Char]]): (Guard, List[List[Char]]) =
    val pos = getNextPos(guard)
    if validatePositionIsInBounds(pos, map) then
      if positionIsValid(pos, map) then
        val newGuard = guard.copy(position = pos)
        val newMap: List[List[Char]] = updateMapWithNewCharacter(pos, guard.direction, map)
        (newGuard, newMap)
      else moveGuard(rotateGuard90Degrees(guard), map)
    else (guard, map) // might need better way to handle
  private def countDistinctPosition(map: List[List[Char]]): Int =
    map.par.map{listChar => listChar.count(validCharacters.contains(_))}.sum
  @tailrec
  private def moveGuardUntilComplete(guard: Guard, map: List[List[Char]]): (Guard, List[List[Char]]) =
    val (nextGuard, nextMap) = moveGuard(guard, map)
    if(guard.position == nextGuard.position) then
      (nextGuard, nextMap)
    else moveGuardUntilComplete(nextGuard, nextMap)
  private def getAllPositionsWithoutObstruction(map: List[List[Char]]): ParSeq[(Int, Int)] =
    map.zipWithIndex.par
      .flatMap((charList, xindx) =>
        charList.zipWithIndex.map(
          (c, yindx) => if c == '.' then (xindx, yindx) else (-1, -1)
        )
      )
      .filter(coord => coord != (-1,-1))
  private def addObstructionToMap(pos: (Int, Int), map: List[List[Char]]): List[List[Char]] =
    map.zipWithIndex.map { (charList, indx) =>
      if indx == pos._1 then charList.zipWithIndex.map((c, indx) => if indx == pos._2 then '#' else c)
      else charList
    }
  private def stringifyMap(map: List[List[Char]]): String =
    map.map(_.mkString).mkString("\n")
  @tailrec
  @tailrec
  private def trackTillGuardCircles(guard: Guard, map: List[List[Char]], visited: Set[((Int, Int), Int)] = Set.empty, cycleStartFound: Option[((Int, Int), Int)] = None, secondVisitStates: Set[((Int, Int), Int)] = Set.empty): Boolean = {
    val (nextGuard, nextMap) = moveGuard(guard, map)
    val nextState = (nextGuard.position, nextGuard.direction)
    if !validatePositionIsInBounds(nextGuard.position, map) then false
    else cycleStartFound match {
      case None =>
        if visited.contains(nextState) then trackTillGuardCircles(nextGuard, nextMap, Set.empty, Some(nextState), Set.empty)
        else trackTillGuardCircles(nextGuard, nextMap, visited + nextState, None, Set.empty)
      case Some(startState) =>
        if nextState == startState then true
        else trackTillGuardCircles(nextGuard, nextMap, visited, Some(startState), secondVisitStates + nextState)
    }
  }
  def trackFacilityGuardPositions(map: List[List[Char]]): (Int, Guard, List[List[Char]]) =
    val position = searchForGuard(map)
    val direction = getGuardDirection(map)
    val guardChar = getCharForDirection(direction)
    val guard = Guard(position, direction, guardChar)
    val (finalGuard, finalMap) = moveGuardUntilComplete(guard, map)
    (countDistinctPosition(finalMap), finalGuard, finalMap)
  def findObstructionCirclePaths(labMap: List[List[Char]]): Int =
    val position = searchForGuard(labMap)
    val direction = getGuardDirection(labMap)
    val guardChar = getCharForDirection(direction)
    val startingGuard = Guard(position, direction, guardChar)
    getAllPositionsWithoutObstruction(labMap).map{pos =>
      val newMap = addObstructionToMap(pos, labMap)
      val tracked = trackTillGuardCircles(startingGuard, newMap)
      if tracked then 1 else 0
    }.sum
}
