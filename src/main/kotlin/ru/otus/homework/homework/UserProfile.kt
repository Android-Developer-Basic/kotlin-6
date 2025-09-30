@file:Suppress("RemoveRedundantQualifierName")

package ru.otus.homework.homework

import kotlin.reflect.KProperty

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
           return ProfileLogImpl(fullName, email)
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
private class ProfileImplementation(propFullName: String, propEmail: String): UserProfile{
    override var fullName: String by FullNameValidator(propFullName)// Использовать NonEmptyStringDelegate() для NonEmptyStringDelegateTest
    override var email: String by EmailValidator(propEmail)
}

private class FullNameValidator(private var fullName: String){
    /*
        Геттер поля fullName
    */
    operator fun getValue(thisRef: UserProfile, property: KProperty<*>): String {
        return fullName
    }

    /*
        Сеттер поля fullName. Если fullName пустой - не устанавливаем значение и не бросаем Exception
     */
    operator fun setValue(thisRef: UserProfile, property: KProperty<*>, fullNameValue:String ){
        if( fullNameValue.isNotBlank() ) {
            fullName = fullNameValue
        }
    }
}
private class EmailValidator(private var email: String){
    /*
    Геттер поля email
     */
    operator fun getValue(thisRef: UserProfile, property: KProperty<*>): String {
        return email
    }

    /*
        Сеттер поля email. Если email кривой - не устанавливаем значение и не бросаем Exception
     */
    operator fun setValue(thisRef: UserProfile, property: KProperty<*>, emailValue:String ){
        if( emailRegex.matches(emailValue) ) {
            email = emailValue
            return
        }
    }

}


private class ProfileLogImpl(userName:String, email:String): UserProfile.Logging {
    private var userProfile: UserProfile = ProfileImplementation(userName, email)
    private var log = mutableListOf<String>()
    override var fullName: String
        get() = userProfile.fullName
        set(value) {
            if(value != userProfile.fullName){
                log.add("Changing `fullName` from '${userProfile.fullName}' to '$value'")
                userProfile.fullName = value
            }
        }
    override var email: String
        get() = userProfile.email
        set(value) {
            if(value != userProfile.email){
                log.add("Changing `email` from '${userProfile.email}' to '$value'")
                userProfile.email = value
            }
        }

    override fun getLog(): List<String> {
        return log.toList()
    }

}