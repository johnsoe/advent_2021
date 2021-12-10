package tasks

import InputParser
import java.util.*
import kotlin.Int
import kotlin.String

public object Nine : InputParser<Int>() {
  public override fun getFileName(): String = "nine.txt"

  public override fun first(): Int {
    val width = getInputByLine().first().length
    val chunk = getGridsSingleLine()
    return chunk.filterIndexed { index, i ->
      val neighbors = getNeighborIndices(index, width, chunk.size)
      neighbors.all { i < chunk[it] }
    }.sumOf { it + 1 }
  }

  private fun getGridsSingleLine(): List<Int> {
    return getInputByChunk().joinToString("")
      .toCharArray()
      .map { it.toInt() - 48 }
      .filter { it != -16 }
  }

  private fun getNeighborIndices(index: Int, width: Int, size: Int): Set<Int> {
    val neighbors = mutableSetOf<Int>()
    if (index >= width) neighbors.add(index - width)
    if (index + width < size) neighbors.add(index + width)
    if (index % width != 0) neighbors.add(index - 1)
    if ((index + 1) % width != 0) neighbors.add(index + 1)
    return neighbors
  }

  public override fun second(): Int {
    val width = getInputByLine().first().length
    val chunk = getGridsSingleLine()
    return chunk.mapIndexed { index, i ->
      val neighbors = getNeighborIndices(index, width, chunk.size)
      if (neighbors.all { i < chunk[it] }) {
        getBasinSize(chunk, width, LinkedList<Int>().apply { add(index) }, mutableSetOf())
      } else {
        1
      }
    }.sortedDescending()
      .take(3)
      .reduce { acc, i -> acc * i }
  }

  private fun getBasinSize(chunk: List<Int>, width: Int, toCheck: Queue<Int>, checked: MutableSet<Int>): Int {
    return if (toCheck.isEmpty()) {
      checked.size
    } else {
      val index = toCheck.remove()
      if (chunk[index] != 9 && !checked.contains(index)) {
        checked.add(index)
        toCheck.addAll(getNeighborIndices(index, width, chunk.size))
      }
      getBasinSize(chunk, width, toCheck, checked)
    }
  }
}
