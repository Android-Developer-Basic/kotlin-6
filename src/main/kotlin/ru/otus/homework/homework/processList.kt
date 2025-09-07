package ru.otus.homework.homework

inline fun processList(list: List<Int>, action: (Int) -> Unit) {
    for (item in list) {
        action(item)
    }
}

fun skipThreeAndPrint(list: List<Int>) {
    processList(list) {
        if (list.indexOf(it) == 2) return@processList
        println("Processing $it")
    }

}
