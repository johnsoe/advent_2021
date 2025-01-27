import kotlin.Int
import util.InputParser
import util.grid.Grid
import util.grid.toGrid
import util.hash.toKnotHash
import java.util.LinkedList

val inputParser = InputParser("2017/14/input.txt")
println(partOne())
println(partTwo())

fun partOne() = toGrid().count { it == '1' }

fun toGrid(): Grid<Char> {
    val input = inputParser.line()
    val gridStrs = (0..127).map {
        val base = "$input-$it"
        base.toKnotHash()
            .map { c ->
                c.digitToInt(16)
                    .toString(2)
                    .padStart(4, '0')
            }.joinToString("")
    }
    return gridStrs.map { it.toCharArray().toList() }.toGrid()
}

fun partTwo(): Int {
    val grid = toGrid()
    val visited = mutableSetOf<Int>()
    var groupCount = 0
    grid.forEachIndexed { index, c ->
        if (c == '1' && index !in visited) {
            groupCount++
            val toVisit = LinkedList<Int>()
            toVisit.add(index)
            while(toVisit.isNotEmpty()) {
                val next = toVisit.removeFirst()
                grid.getAdjacentNeighbors(next).filter {
                    it.value == '1' && it.index !in visited
                }.forEach {
                    visited.add(it.index)
                    toVisit.add(it.index)
                }
            }
        }
    }
    return groupCount
}
