package ru.otus.homework.tasks


data class Book(
    val cover: String,
    val mainPart: String,
    val additionalInfo: String
)

interface BookBuilder {
    fun printCover(): String
    fun printMainPart(): String
    fun printAdditionalInformation(): String
    fun getBook(): Book
}

class ShineBookBuilder : BookBuilder {
    override fun printMainPart(): String {
        println("Shine")
        return "Shine"
    }

    override fun printCover(): String {
        println("Daddy's home")
        return "Daddy's home"
    }

    override fun printAdditionalInformation(): String {
        println("All reviews and suggests must be folded between your ass halfs")
        return "All reviews and suggests must be folded between your ass halfs"

    }

    override fun getBook(): Book =
        Book(printCover(), printMainPart(), printAdditionalInformation())

}

fun main() {
    val bookBuilder = ShineBookBuilder()
    bookBuilder.getBook()
}