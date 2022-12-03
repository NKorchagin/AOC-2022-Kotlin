package day03

import readInput
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@ExperimentalTime
fun main() {

    fun Char.priority(): Int = code - if (isUpperCase()) 38 else 96

    fun findShareItem(line: String): Char {
        val firstCompartment = line.take(line.length / 2).toSet()
        val secondCompartment = line.takeLast(line.length / 2).toSet()
        return (firstCompartment intersect secondCompartment).first()
    }

    fun findShareItemPerGroup(lines: List<String>): Char {
        return lines
            .map { it.toSet() }
            .reduce { acc, set -> acc intersect set }
            .first()
    }

    fun solveA(fileName: String): Long {
        val input = readInput(fileName)
        return input
            .sumOf { findShareItem(it).priority() }
            .toLong()
    }

    fun solveB(fileName: String): Long {
        val input = readInput(fileName)
        return input
            .chunked(3)
            .sumOf { findShareItemPerGroup(it).priority() }
            .toLong()
    }

    check(solveA("day03/Example") == 157L)
    check(solveB("day03/Example") == 70L)

    val input = "day03/Input.ignore"
    val (part1, time1) = measureTimedValue { solveA(input) }
    println("Part1: $part1 takes: ${time1.inWholeMilliseconds}ms")
    val (part2, time2) = measureTimedValue { solveB(input) }
    println("Part2: $part2 takes: ${time2.inWholeMilliseconds}ms")
}
