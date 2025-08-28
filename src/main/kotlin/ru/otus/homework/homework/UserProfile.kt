@file:Suppress("RemoveRedundantQualifierName")

package ru.otus.homework.homework

import java.util.LinkedList
import kotlin.properties.Delegates.vetoable

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
        fun create(fullName: String, email: String): UserProfile {
            return ProfileImplementation(fullName, email)
        }
        /**
         * Creates user profile with logging
         */
        fun createWithLogging(fullName: String, email: String): UserProfile.Logging {
            return ProfileWithLoggingImplementation(create(fullName, email))
        }
    }
}

/**
 * Реализация простого [UserProfile].
 */
private class ProfileImplementation(fullNameStr: String, email: String): UserProfile {
    override var email: String by vetoable(email) {_, _, newValue -> newValue.isNotBlank() && Regex("^[A-Za-z](.*)([@])(.+)(\\.)(.+)").matches(newValue)}
    override var fullName: String by NonEmptyStringDelegate()

    init {
        fullName = fullNameStr
    }
}

private class ProfileWithLoggingImplementation(private val profile: UserProfile): UserProfile.Logging, UserProfile by profile {
    private val log = LinkedList<String>()

    override var fullName: String
        get() = profile.fullName
        set(value) {
            log.add("Changing `fullName` from '${profile.fullName}' to '$value'")
            profile.fullName = value
        }

    override var email: String
        get() = profile.email
        set(value) {
            log.add("Changing `email` from '${profile.email}' to '$value'")
            profile.email = value
        }

    override fun getLog(): List<String> = log
}