package ru.otus.homework.mypatterns


// object
object MySingletonObject

// Synchronized Initialization
class MySingletonSyncInit private constructor() {
    companion object {

        @Volatile
        private var instance: MySingletonSyncInit? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MySingletonSyncInit().also { instance = it }
        }
    }
}

// Lazy Initialization
class MySingletonLazyInit private constructor() {
    companion object {
        val instance: MySingletonLazyInit by lazy { MySingletonLazyInit() }
    }
}

// Double-Locking
class MySingletonDoubleLock private constructor() {
    companion object {
        @Volatile
        private var instance: MySingletonDoubleLock? = null

        fun getInstance(): MySingletonDoubleLock {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = MySingletonDoubleLock()
                    }
                }
            }
            return instance!!
        }
    }
}

// Double-Locking
enum class MySingletonEnum {
    INSTANCE
}