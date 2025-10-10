@file:Suppress("RemoveRedundantQualifierName")

package ru.otus.homework.homework

import ru.otus.homework.homework.UserProfile
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
            return ProfileWithLoggingImplementation( fullName , email )
        }
    }
}

/*
 * Реализация простого [UserProfile].
 */
private class ProfileImplementation(  _fullName: String,  email: String): UserProfile{

    override var email: String by vetoable(email) {_, _, newValue -> newValue.isNotBlank() && Regex("^[A-Za-z](.*)([@])(.+)(\\.)(.+)").matches(newValue)}
    override var fullName: String by NonEmptyStringDelegate( )
    init {  fullName = _fullName  }
}

private class ProfileWithLoggingImplementation(  val personFullName: String, val personEmail: String ): UserProfile.Logging {

    private var profileLog  = mutableListOf<String>()
    private var userProfile = ProfileImplementation( personFullName, personEmail )

    override var fullName: String
        get() = userProfile.fullName
        set(value) {
            profileLog.add("Changing `fullName` from '${userProfile.fullName}' to '$value'")
            userProfile.fullName = value
        }

    override var email: String
        get() = userProfile.email
        set(value) {
            profileLog.add("Changing `email` from '${userProfile.email}' to '$value'")
            userProfile.email = value
        }

    override fun getLog(): List<String>{
        return profileLog.toList()
    }
}