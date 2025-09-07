package ru.otus.homework.homework

inline fun processList(list: List<Int>, action: (Int,Int) -> Unit) {
    for ((index, item) in list.withIndex()) {
        action(index, item)
    }
}

fun skipThreeAndPrint(list: List<Int>) {
    processList(list) { index, value ->
        if (index == 2) return@processList
        println("Processing $value")
    }
}