package ru.ifmo.rain.balahnin.parser

import ru.ifmo.rain.balahnin.exceptions.UnexpectedTokenException
import ru.ifmo.rain.balahnin.exceptions.prepareExceptionMessage
import ru.ifmo.rain.balahnin.expression.ExpressionNode
import ru.ifmo.rain.balahnin.lexer.Token
import ru.ifmo.rain.balahnin.lexer.TokenType.*


private lateinit var tokens: List<Token>
private var current = 0

fun parse(input: List<Token>): ExpressionNode {
    tokens = input
    current = 0
    return wrappedAddition()
}

private fun wrappedAddition(): ExpressionNode {
    val res = ExpressionNode("Add")
    return when (tokens[current].type) {
        MINUS, NUMBER, OPEN_BRACKET -> {
            res.children.add(wrappedMulDiv())
            res.children.add(realAddition())
            res
        }
        else -> throw UnexpectedTokenException(prepareExceptionMessage(tokens, current))
    }
}

private fun realAddition(): ExpressionNode {
    val res = ExpressionNode("Add'")
    return when (tokens[current].type) {
        EOF, CLOSE_BRACKET -> res
        MINUS, PLUS -> {
            res.children.add(ExpressionNode(tokens[current].toString(), true))
            next()
            res.children.add(wrappedMulDiv())
            res.children.add(realAddition())
            res
        }
        else -> throw UnexpectedTokenException(prepareExceptionMessage(tokens, current))
    }
}


private fun wrappedMulDiv(): ExpressionNode {
    val res = ExpressionNode("Mul")
    return when (tokens[current].type) {
        MINUS, NUMBER, OPEN_BRACKET -> {
            res.children.add(wrappedUnary())
            res.children.add(realMulDiv())
            res
        }
        else -> throw UnexpectedTokenException(prepareExceptionMessage(tokens, current))
    }
}

private fun realMulDiv(): ExpressionNode {
    val res = ExpressionNode("Mul'")
    return when (tokens[current].type) {
        PLUS, MINUS, EOF, CLOSE_BRACKET -> res
        MUL, DIV -> {
            res.children.add(ExpressionNode(tokens[current].toString(), true))
            next()
            res.children.add(wrappedUnary())
            res.children.add(realMulDiv())
            res
        }
        else -> throw UnexpectedTokenException(prepareExceptionMessage(tokens, current))
    }
}

private fun wrappedUnary(): ExpressionNode {
    val res = ExpressionNode("Unary")
    return when (tokens[current].type) {
        MINUS -> {
            res.children.add(realUnary())
            res
        }
        NUMBER, OPEN_BRACKET -> {
            res.children.add(primary())
            res
        }
        else -> throw UnexpectedTokenException(prepareExceptionMessage(tokens, current))
    }
}

private fun realUnary(): ExpressionNode {
    val res = ExpressionNode("Unary'")
    return when (tokens[current].type) {
        EOF -> res
        MINUS -> {
            res.children.add(ExpressionNode(tokens[current].toString(), true))
            next()
            res.children.add(realUnary())
            res
        }
        NUMBER, OPEN_BRACKET -> {
            res.children.add(primary())
            res
        }
        else -> throw UnexpectedTokenException(prepareExceptionMessage(tokens, current))
    }
}

private fun primary(): ExpressionNode {
    val res = ExpressionNode("Primary")
    return when (tokens[current].type) {
        OPEN_BRACKET -> {
            res.children.add(ExpressionNode(tokens[current].toString(), true))
            next()
            res.children.add(wrappedAddition())
            if (tokens[current].type != CLOSE_BRACKET) {
                throw UnexpectedTokenException(prepareExceptionMessage(tokens, current))
            }
            res.children.add(ExpressionNode(")"))
            next()
            res
        }
        NUMBER -> {
            res.children.add(ExpressionNode(tokens[current].toString(), true))
            next()
            res
        }
        else -> throw UnexpectedTokenException(prepareExceptionMessage(tokens, current))
    }
}

private fun next() {
    if (tokens[current].type != EOF) {
        current++
    } else {
        throw UnexpectedTokenException(prepareExceptionMessage(tokens, current))
    }

}