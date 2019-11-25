package ru.ifmo.rain.balahnin.lexer

enum class TokenType {
    OPEN_BRACKET{
        override fun toString(): String = "("
    },
    CLOSE_BRACKET{
        override fun toString(): String = ")"
    },
    PLUS{
        override fun toString(): String = "+"
    },
    MINUS{
        override fun toString(): String = "-"
    },
    MUL{
        override fun toString(): String = "*"
    },
    DIV{
        override fun toString(): String = "/"
    },
    NUMBER,
    EOF
}