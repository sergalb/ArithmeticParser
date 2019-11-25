package ru.ifmo.rain.balahnin

import org.junit.Test
import ru.ifmo.rain.balahnin.lexer.scan
import ru.ifmo.rain.balahnin.parser.parse

class BigTests {

    @Test
    fun sequenceOfMinus() {
        //given
        val input = "-----42"

        //when
        val tokens = scan(input)
        parse(tokens)

        //then shouldn't fail
        assert(true)
    }

    @Test
    fun sequenceOfBrackets() {
        //given
        val input = "(((42 - 5)))"

        //when
        val tokens = scan(input)
        parse(tokens)

        //then shouldn't fail
        assert(true)
    }

    @Test
    fun minusesWithBrackets() {
        //given
        val input = "--(-(---(42)))"

        //when
        val tokens = scan(input)
        parse(tokens)

        //then shouldn't fail
        assert(true)
    }

    @Test
    fun bigExpression() {

        //given
        val input = "(11+2)*(-3*(7-4)+2)- --5 * (123 + 34) / (12 - (213 * (98 - 56)))"


        //when
        val tokens = scan(input)
        parse(tokens)

        //then shouldn't fail
        assert(true)
    }


}