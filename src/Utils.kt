package utils

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name")
    .readLines()

fun readInputText(name: String) = File("src", "$name")
    .readText()
    .dropLastWhile { it == '\n' }

fun readInputGroups(name: String) = File("src", "$name")
    .readText()
    .dropLastWhile { it == '\n' }
    .split("\n" + "\n")
    .map { it.split("\n") }

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun IntRange.inside(range: IntRange) = first in range && last in range
fun IntRange.overlaps(range: IntRange) = first in range || last in range

fun String.splitToPair(delimiter: String) = split(delimiter).let { it.first() to it.last() }

