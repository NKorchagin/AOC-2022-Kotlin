package day06

import utils.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
fun main() {

    fun String.findFirstUniqueSequenceEndIndex(length: Int): Int =
        windowed(length)
            .indexOfFirst { it.toSet().size == length } + length

    fun solveA(fileName: String): Long {
        return readInputText(fileName)
            .findFirstUniqueSequenceEndIndex(4)
            .toLong()
    }

    fun solveB(fileName: String): Long {
        return readInputText(fileName)
            .findFirstUniqueSequenceEndIndex(14)
            .toLong()
    }

    check(solveA("day06/Example") == 7L)
    check(solveB("day06/Example") == 19L)

    val input = "day06/Input.ignore"
    val (part1, time1) = measureTimedValue { solveA(input) }
    println("Part1: $part1 takes: ${time1.inWholeMilliseconds}ms")
    val (part2, time2) = measureTimedValue { solveB(input) }
    println("Part2: $part2 takes: ${time2.inWholeMilliseconds}ms")
}
