package ru.ifmo.rain.balahnin

import ru.ifmo.rain.balahnin.lexer.scan
import ru.ifmo.rain.balahnin.parser.parse
import ru.ifmo.rain.balahnin.print.printTree


fun main() {
    val input = readLine()
    val tokens = scan(input!!)
    val expression = parse(tokens)
    printTree(expression)
}