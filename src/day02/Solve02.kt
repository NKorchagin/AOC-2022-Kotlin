package day02

import readInput
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue
import day02.RPS.*

enum class RPS(val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}

fun RPS.customCompare(other: RPS): Int = when {
    this == ROCK && other == SCISSORS -> 1
    this == SCISSORS && other == ROCK -> -1
    else -> score.compareTo(other.score)
}
infix fun RPS.beats(other: RPS) = this.customCompare(other) > 0
infix fun RPS.loses(other: RPS) = this.customCompare(other) < 0
infix fun RPS.ties(other: RPS) = this.customCompare(other) == 0

fun Char.toRPS(): RPS = when (this) {
    'A', 'X' -> ROCK
    'B', 'Y' -> PAPER
    'C', 'Z' -> SCISSORS
    else -> error("Unknown $this")
}

@ExperimentalTime
fun main() {
    fun roundOutcome(opponent: RPS, your: RPS): Int = when {
        your beats opponent -> 6
        your loses opponent -> 0
        your ties opponent -> 3
        else -> error("Unexpected round outcome")
    }

    fun totalRoundScore(opponent: RPS, your: RPS): Int = roundOutcome(opponent, your) + your.score

    fun smartChoice(opponent: RPS, strategy: Char): RPS = when (strategy) {
        'X' -> RPS.values().first { it loses opponent }  // lose strategy
        'Y' -> opponent // tie strategy
        'Z' -> RPS.values().first { it beats opponent } // win strategy
        else -> error("Unknown $strategy")
    }

    fun totalSmartRoundScore(opponent: RPS, strategy: Char): Int =
        totalRoundScore(opponent, smartChoice(opponent, strategy))

    fun solveA(fileName: String): Long {
        val input = readInput(fileName)
        return input
            .sumOf { totalRoundScore(it.first().toRPS(), it.last().toRPS()) }
            .toLong()
    }

    fun solveB(fileName: String): Long {
        val input = readInput(fileName)
        return input
            .sumOf { totalSmartRoundScore(it.first().toRPS(), it.last()) }
            .toLong()
    }

    check(solveA("day02/Example") == 15L)
    check(solveB("day02/Example") == 12L)

    val input = "day02/Input.ignore"
    val (part1, time1) = measureTimedValue { solveA(input) }
    println("Part1: $part1 takes: ${time1.inWholeMilliseconds}ms")
    val (part2, time2) = measureTimedValue { solveB(input) }
    println("Part2: $part2 takes: ${time2.inWholeMilliseconds}ms")

    // Alternative solution
    fun solveAMath(fileName: String): Long {
        return readInput(fileName)
            .map { it[0] - 'A' to it[2] - 'X' }
            .sumOf { it.second + 1 + listOf(3, 6, 0)[(it.second - it.first + 3) % 3] }
            .toLong()
    }

    fun solveBMath(fileName: String): Long {
        return readInput(fileName)
            .map { it[0] - 'A' to it[2] - 'X' }
            .sumOf { it.second * 3 + (it.first + it.second + 2) % 3 + 1 }
            .toLong()
    }

    check(solveAMath("day02/Example") == 15L)
    check(solveBMath("day02/Example") == 12L)

    val (part1Math, time1Math) = measureTimedValue { solveAMath(input) }
    println("Part1_math: $part1Math takes: ${time1Math.inWholeMilliseconds}ms")
    val (part2Math, time2Math) = measureTimedValue { solveAMath(input) }
    println("Part2_math: $part2Math takes: ${time2Math.inWholeMilliseconds}ms")
}