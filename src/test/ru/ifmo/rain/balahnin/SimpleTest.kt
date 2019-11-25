package ru.ifmo.rain.balahnin

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import ru.ifmo.rain.balahnin.expression.ExpressionNode
import ru.ifmo.rain.balahnin.lexer.scan
import ru.ifmo.rain.balahnin.parser.parse

class SimpleTest {

    private fun getExpressionNodeWithNum(number: Int): ExpressionNode {
        return ExpressionNode("Mul").apply {
            children.add(
                ExpressionNode("Unary").apply {
                    children.add(ExpressionNode("Primary").apply {
                        children.add(ExpressionNode(number.toString()))
                    })
                }
            )
        }
    }

    @Test
    fun numberOneShouldParse() {
        //given
        val input = "1"
        val correctExpression = ExpressionNode("Add").apply {
            children.add(getExpressionNodeWithNum(1))
        }

        //when
        val tokens = scan(input)
        val expression = parse(tokens)

        //then
        assertEquals(correctExpression, expression)
    }

    @Test
    fun numberMinusOneShouldParse() {
        //given
        val input = "- 1"
        val correctExpression = ExpressionNode("Add").apply {
            children.add(
                ExpressionNode("Mul").apply {
                    children.add(
                        ExpressionNode("Unary").apply {
                            children.add(
                                ExpressionNode("Unary'").apply {
                                    children.add(ExpressionNode("-"))
                                    children.add(ExpressionNode("Primary").apply {
                                        children.add(ExpressionNode("1"))
                                    })
                                }
                            )
                        })
                })
        }

        //when
        val tokens = scan(input)
        val expression = parse(tokens)

        //then
        assertEquals(correctExpression, expression)
    }

    @Test
    fun onePlusTwoShouldParse() {
        //given
        val input = "1 +    2"
        val correctExpression = ExpressionNode("Add").apply {
            children.add(
               getExpressionNodeWithNum(1)
            )
            children.add(
                ExpressionNode("+")
            )
            children.add(
                getExpressionNodeWithNum(2)
            )

        }

        //when
        val tokens = scan(input)
        val expression = parse(tokens)

        //then
        assertEquals(correctExpression, expression)
    }

    @Test
    fun expressionWithBracketsShouldParse() {
        //given
        val input = "(1 - 2) * 33"
        val correctExpression = ExpressionNode("Add").apply {
            children.add(
                ExpressionNode("Mul").apply {
                    children.add(
                        ExpressionNode("Unary").apply {
                            children.add(
                                ExpressionNode("Primary").apply {
                                    children.add(ExpressionNode("("))
                                    children.add(ExpressionNode("Add").apply{
                                        children.add(getExpressionNodeWithNum(1))
                                        children.add(ExpressionNode("Add'").apply {
                                            children.add(ExpressionNode("-"))
                                            children.add(getExpressionNodeWithNum(2))
                                        })
                                    })
                                }
                            )
                        }
                    )
                    children.add(ExpressionNode("Mul'").apply {
                        children.add(ExpressionNode("*"))
                        children.add(ExpressionNode("Unary").apply {
                            children.add(ExpressionNode("Primary").apply {
                                ExpressionNode("33")
                            })
                        })
                    })
                }
            )
        }

        //when
        val tokens = scan(input)
        val expression = parse(tokens)

        //then
        assertEquals(correctExpression, expression)
    }

}