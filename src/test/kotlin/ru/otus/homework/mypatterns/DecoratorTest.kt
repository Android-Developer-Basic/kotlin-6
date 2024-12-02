package ru.otus.homework.mypatterns

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DecoratorTest {
    @Test
    fun logDecorator() {
        val operation = DummyOperation("Test operation")
        val decorator = LogOperationDecorator(operation)

        val operationResult = operation.proceed()
        val decoratorResult = decorator.proceed()

        Assertions.assertEquals(operationResult, decoratorResult)
    }

    @Test
    fun measureTimeDecorator() {
        val operation = DummyOperation("Test operation")
        val decorator = MeasureOperationTimeDecorator(operation)

        val operationResult = operation.proceed()
        val decoratorResult = decorator.proceed()

        Assertions.assertEquals(operationResult, decoratorResult)
    }

    @Test
    fun lambdaDecorator() {
        val operation = DummyOperation("Test operation")
        val decorator = LambdaOperationDecorator(
            operation,
            before = { println("Before") },
            after = { println("After") },
        )

        val operationResult = operation.proceed()
        val decoratorResult = decorator.proceed()

        Assertions.assertEquals(operationResult, decoratorResult)
    }
}