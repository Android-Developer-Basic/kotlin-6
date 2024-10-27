package ru.otus.homework.patterns

import kotlin.random.Random

interface Command {
    fun generate(): String
    fun count()
}

object GeneratedString: Command {

    override fun generate(): String = CharArray(50)
        .map {Random.nextInt(97,122).toChar()}
        .joinToString()

    override fun count() {
       generate().count()

}

class DistinctCount(private val generatedString: GeneratedString): Command {

    override fun generate(): String = generatedString.generate()

    override fun count() {
       println((generate().map { arrayOf(it).distinct() }).count())
    }
}

class PredicateCount(private val generatedString: GeneratedString): Command {

    override fun generate(): String = generatedString.generate()

    override fun count() {
        println(generate().count { it == 's' })
    }
}

class Executor{

    fun execute(command: Command) = command.count()
}

fun main(){
    val string = GeneratedString
    val distinct = DistinctCount(string)
    val predicate = PredicateCount(string)
    val executor = Executor()

    executor.execute(distinct)
    executor.execute(predicate)
}
}
