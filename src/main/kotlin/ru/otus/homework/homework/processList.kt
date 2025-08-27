package ru.otus.homework.homework

inline fun processList(list: List<Int>, action: (Int) -> Unit) {
    for (item in list) {
        action(item)
    }
}

fun skipThreeAndPrint(list: List<Int>) {
    var counter = 1
    processList(list) {
        if (counter != 3)
            /* тут пришлось помучиться,
            * т.к. тесты НЕ проходили, если использовать функцию println()
            * пришлось переключиться на print(...\n)
            */
            print("Processing $it\n")
        counter ++
    }
}