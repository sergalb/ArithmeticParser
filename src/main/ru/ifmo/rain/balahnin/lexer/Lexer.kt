package ru.ifmo.rain.balahnin.lexer

import ru.ifmo.rain.balahnin.exceptions.LexingException
import ru.ifmo.rain.balahnin.exceptions.prepareExceptionMessage
import java.lang.Character.isDigit
import java.lang.Character.isWhitespace


private val tokens: MutableList<Token> = ArrayList()
private var current: Int = 0
private lateinit var expression: String
private val END_CHAR = 'e'
fun scan(input: String): List<Token> {
    expression = input
    tokens.clear()
    current = 0
    while (current < expression.length) {
        skipWhiteSpace()
        if (current == expression.length) {
            break
        }
        readNext()
    }
    tokens.add(Token(TokenType.EOF, null))
    return tokens
}

private fun readNext() {
    when (val curSymbol = expression[current++]) {
        '(' -> addToken(TokenType.OPEN_BRACKET, null)
        ')' -> addToken(TokenType.CLOSE_BRACKET, null)
        '+' -> addToken(TokenType.PLUS, null)
        '-' -> addToken(TokenType.MINUS, null)
        '*' -> addToken(TokenType.MUL, null)
        '/' -> addToken(TokenType.DIV, null)
        else -> {
            if (isDigit(curSymbol)) {
                addToken(TokenType.NUMBER, readNumber())
            } else if (current <= expression.length) {
                throw LexingException(
                    prepareExceptionMessage(
                        expression,
                        current - 1
                    )
                )
            }
        }

    }
}

private fun readNumber(): Long {
    val start = current - 1
    while (isDigit(peek())) {
        current++
    }
    return expression.substring(start, current).toLong()
}

private fun addToken(type: TokenType, tokenValue: Any?) {
    tokens.add(Token(type, tokenValue))
}

private fun skipWhiteSpace() {
    while (isWhitespace(peek())) {
        current++
    }
}

private fun peek(): Char {
    if (current >= expression.length) return END_CHAR
    return expression[current]
}

