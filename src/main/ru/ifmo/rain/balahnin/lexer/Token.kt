package ru.ifmo.rain.balahnin.lexer

data class Token (
    val type: TokenType,
    val value: Any?
)
{
    override fun toString(): String {
        if (value != null) {
            return value.toString()
        }
        return type.toString()
    }
}