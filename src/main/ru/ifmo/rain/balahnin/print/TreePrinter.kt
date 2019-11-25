package ru.ifmo.rain.balahnin.print

import ru.ifmo.rain.balahnin.expression.ExpressionNode

private val STEP = 7
fun printTree(expression: ExpressionNode, depth: Int = 0) {
    if (expression.children.size == 0) {
        if (expression.isPrintable) {
            println(" ".repeat(depth) + "|${expression.name}")
        }
        return
    }

    println(" ".repeat(depth) + "|${expression.name}->")
    for (child in expression.children) {
        printTree(child, depth + STEP)
    }
}