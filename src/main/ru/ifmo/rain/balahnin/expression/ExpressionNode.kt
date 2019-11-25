package ru.ifmo.rain.balahnin.expression

data class ExpressionNode(
    val name: String,
    val isPrintable: Boolean = false
) {
    val children: MutableList<ExpressionNode>

    init {
        children = ArrayList()

    }
}
