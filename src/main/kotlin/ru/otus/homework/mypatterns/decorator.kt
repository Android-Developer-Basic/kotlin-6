package ru.otus.homework.mypatterns

import kotlin.time.measureTime

interface Operation {
    val name: String
    fun proceed(): Int
}

class DummyOperation(override val name: String) : Operation {
    override fun proceed(): Int {
        println("Operation $name proceeding")
        return 1
    }

}

abstract class OperationDecorator(protected val operation: Operation) : Operation {
    override val name: String
        get() = operation.name

    override fun proceed(): Int {
        return operation.proceed()
    }
}

abstract class BeforeAfterOperationDecorator(operation: Operation) : OperationDecorator(operation) {
    abstract fun before()

    abstract fun after()

    override fun proceed(): Int {
        before()
        val result = operation.proceed()
        after()
        return result
    }
}

class LogOperationDecorator(operation: Operation) : BeforeAfterOperationDecorator(operation) {
    override fun before() {
        println("Before operation ${operation.name}")
    }

    override fun after() {
        println("After operation ${operation.name}")
    }
}

class MeasureOperationTimeDecorator(operation: Operation) : OperationDecorator(operation) {
    override fun proceed(): Int {
        var result: Int
        val elapsed = measureTime {
            result = operation.proceed()
        }
        println("Operation ${operation.name} proceeding time: $elapsed")
        return result
    }
}

class LambdaOperationDecorator(operation: Operation, private val before: () -> Unit, private val after: () -> Unit) :
    BeforeAfterOperationDecorator(operation) {
    override fun before() {
        before.invoke()
    }

    override fun after() {
        after.invoke()
    }
}