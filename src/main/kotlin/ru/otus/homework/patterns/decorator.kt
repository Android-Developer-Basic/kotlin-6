package ru.otus.homework.patterns

fun <T> withRetries(retryCount: Int = 3, block: () -> T): () -> T {
    return fun(): T {
        var count = 0
        while (true) {
            try {
                return block()
            } catch (e: Exception) {
                count++
                if (count > retryCount) {
                    throw e
                }
            }
        }
    }
}

fun main() {
    var counter = 0

    val decorated = withRetries {
        if (counter < 2) {
            counter++
            throw Exception("Error")
        }

        "OK"
    }

    println(decorated())
}

