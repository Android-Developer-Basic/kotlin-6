@file:Suppress("RemoveRedundantQualifierName")

package ru.otus.homework.homework

import java.util.LinkedList
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
        fun create(fullName: String, email: String): UserProfile {
            require(fullName.isNotBlank()) { "Full name should not be empty" }
            require(email.isNotBlank() && emailRegex.matches(email)) { "Invalid email" }
            return ProfileImplementation(fullName, email)
        }

        /**
         * Creates user profile with logging
         */
        fun createWithLogging(fullName: String, email: String): UserProfile.Logging {
            return ProfileImplementationWithLogging(
                create(fullName, email))
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
private class ProfileImplementation(fullName: String, email: String): UserProfile{
    override var fullName : String by Delegates.vetoable(fullName) { _, _, newValue ->
        newValue.isNotBlank()}

    override var email : String by Delegates.vetoable(email) { _, _, newValue ->
        newValue.isNotBlank() && emailRegex.matches(newValue)}
}

private class ProfileImplementationWithLogging(private val userProfile: UserProfile) : UserProfile.Logging, UserProfile by userProfile {
    private val log = LinkedList<String>()

    override var fullName: String
        get() = userProfile.fullName
        set(value) {
            log.add("Changing `fullName` from '${userProfile.fullName}' to '$value'")
            userProfile.fullName = value
        }

    override var email: String
        get() = userProfile.email
        set(value) {
            log.add("Changing `email` from '${userProfile.email}' to '$value'")
            userProfile.email = value
        }

    override fun getLog(): List<String> = log
}