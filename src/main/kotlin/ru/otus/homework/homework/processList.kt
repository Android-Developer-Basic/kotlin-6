package ru.otus.homework.homework

inline fun processList(list: List<Int>, action: (Int) -> Unit) {
    for (item in list) {
        action(item)
    }
}

fun skipThreeAndPrint(list: List<Int>) {
    processList(list) { number ->
        if (number == 3) return@processList
        print("Processing $number\n")
    }
}
