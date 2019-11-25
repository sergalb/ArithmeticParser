package ru.ifmo.rain.balahnin.exceptions

import org.junit.Test
import ru.ifmo.rain.balahnin.lexer.scan
import ru.ifmo.rain.balahnin.parser.parse
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class WrongInput {

    @Test
    fun shouldFailWithLexingException(){
        //given
        val input = "1 + e"

        //when
        val exception = assertFailsWith<LexingException> {
            scan(input)
        }
        assertTrue (exception.message!!.contains("unexpected character 'e':"))
    }

    @Test
    fun shouldFailWithUnexpectedTokenException() {
        //given
        val input = "1-/"
        val exception = assertFailsWith<UnexpectedTokenException> {
            parse(scan(input))
        }
        assertTrue(exception.message!!.contains("unexpected character '/':"))
    }

    @Test
    fun hasNotBracketsBalance() {
        val input = "((1-2)"
        assertFailsWith<UnexpectedTokenException> {
            parse(scan(input))
        }
    }

    @Test
    fun twoNumberSequentially() {
        val input = "1 2"
        assertFailsWith<UnexpectedTokenException> {
            parse(scan(input))
        }
    }


}