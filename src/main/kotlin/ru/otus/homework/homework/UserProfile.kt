@file:Suppress("RemoveRedundantQualifierName")

package ru.otus.homework.homework
import kotlin.properties.Delegates

/**
 * Профиль пользователя
 */
interface UserProfile {
    /**
     * Полное имя
     * Не должно принимать пустые строки
     */
    var fullName: String

    /**
     * Email.
     * Не должен принимать пустые и некорректные строки
     */
    var email: String

    /**
     * Профиль с логированием
     */
    interface Logging : UserProfile, WithLogging

    companion object {
        /**
         * Создает профиль пользователя
         */
        //override var email: String by Delegates.vetoable()
        fun create(fullName: String, email: String): UserProfile {
            require(fullName.isNotBlank()) { "Full name should not be empty" }
            require(email.isNotBlank() && emailRegex.matches(email)) { "Invalid email" }
            return ProfileImplementation(fullName, email)
        }

        /**
         * Creates user profile with logging
         */
        fun createWithLogging(fullName: String, email: String): UserProfile.Logging {
            return LoggingProfile(fullName, email)
        }
    }
}

/**
 * Проверка емейла на корректность
 */
private val emailRegex = Regex("^[A-Za-z](.*)([@])(.+)(\\.)(.+)")

/**
 * Реализация простого [UserProfile].
 */
private class ProfileImplementation(initFullNAme: String, initEmail: String): UserProfile {
    override var email: String by Delegates.vetoable(initEmail) { _, old, new ->
        when {
            !emailRegex.matches(new) -> {
                false
            }

            else -> true
        }
    }

    // если бы можно было переделать конструктор NonEmptyStringDelegate(),
    // было бы проще с передачей значения
    private val fullNameDelegate = NonEmptyStringDelegate().apply {
        init(initFullNAme)
    }
    override var fullName: String by fullNameDelegate
}

private class LoggingProfile(initFullName: String, initEmail: String) : UserProfile.Logging {
    private val logs = mutableListOf<String>()

    override fun getLog(): List<String> = logs.toList()

    private val fullNameDelegate = NonEmptyStringDelegate().apply {
        init(initFullName)
    }
    override var fullName: String
        get() = fullNameDelegate.getValue(this, ::fullName)
        set(value) {
            val oldValue = fullName
            fullNameDelegate.setValue(this, ::fullName, value)
            if (fullName != oldValue) {
                addLog("Changing `fullName` from '$oldValue' to '$fullName'")
            } else {
                addLog("Bad FullName: $fullName")
            }
        }

    override var email: String by Delegates.vetoable(initEmail) { _, old, new ->
        val isValid = emailRegex.matches(new)
        if (isValid) {
            addLog("Changing `email` from '$old' to '$new'")
        } else {
            addLog("Bad email: $new")
        }
        isValid
    }

    private fun addLog(message: String) {
        logs.add(message)
    }
}