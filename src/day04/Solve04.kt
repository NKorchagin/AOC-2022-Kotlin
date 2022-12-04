package day04

import utils.*
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
fun main() {

    fun readRangePairs(fileName: String) =
        readInput(fileName)
            .map { line ->
                line.split(",")
                    .map { it.splitToPair("-") }
                    .map { it.first.toInt() .. it.second.toInt() }
            }
            .map { it.first() to it.last() }

    fun solveA(fileName: String): Long =
        readRangePairs(fileName)
            .count { (left, right) -> left.inside(right) || right.inside(left) }
            .toLong()

    fun solveB(fileName: String): Long =
        readRangePairs(fileName)
            .count { (left, right) -> left.overlaps(right) || right.overlaps(left) }
            .toLong()

    check(solveA("day04/Example") == 2L)
    check(solveB("day04/Example") == 4L)

    val input = "day04/Input.ignore"
    val (part1, time1) = measureTimedValue { solveA(input) }
    println("Part1: $part1 takes: ${time1.inWholeMilliseconds}ms")
    val (part2, time2) = measureTimedValue { solveB(input) }
    println("Part2: $part2 takes: ${time2.inWholeMilliseconds}ms")
}
