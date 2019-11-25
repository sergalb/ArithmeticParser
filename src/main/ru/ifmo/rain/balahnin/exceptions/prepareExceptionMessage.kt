package ru.ifmo.rain.balahnin.exceptions

import ru.ifmo.rain.balahnin.lexer.Token
import kotlin.math.max
import kotlin.math.min

fun prepareExceptionMessage(expression: List<Token>, position: Int): String {
    var wrongTokenPosition = 0
    val sb = StringBuilder()
    for (i in expression.indices) {
        val str = expression[i].toString()
        if (i < position) {
            wrongTokenPosition += str.length + 1
        }
        sb.append("$str ")
    }
    return prepareExceptionMessage(sb.toString(), wrongTokenPosition)
}


fun prepareExceptionMessage(expression: String, position: Int): String {
    var start = 0
    var end = expression.length
    if (expression.length >= 50) {
        start = max(position - 25, 0)
        end = min(end, position + 25)
    }
    return drawWrongPlace(expression.substring(start, end), position - start)
}

private fun drawWrongPlace(expression: String, position: Int): String =
    System.lineSeparator() + "unexpected character \'${expression[position]}\': " + expression +
            System.lineSeparator() + " ".repeat(26) + "-".repeat(position) + "^" + "-".repeat(
        expression.length - position - 1
    )