package ru.otus.homework.homework

import kotlin.reflect.KProperty





/**
 * Delegate that allows to set non-empty string value
 */
class NonEmptyStringDelegate() {
    private var string: String = ""

    constructor(stringValue: String): this()
    {
        string = stringValue
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String = string

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: String) {
        if(newValue.isNotBlank()){
            string = newValue
        }
    }
}

interface StringDelegate{

    public operator  fun getValue(thisRef: Any?, property: KProperty<*>): String

    public operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: String)
}

class StringProperty(private var propertyValue: String): StringDelegate{
    override fun getValue(thisRef: Any?, property: KProperty<*>): String = propertyValue

    override fun setValue(thisRef: Any?,  property: KProperty<*>, newValue: String) {
        propertyValue = newValue
    }

}

abstract class StringDecorator(protected val stringDelegate: StringDelegate): StringDelegate{

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): String = stringDelegate.getValue(thisRef, property)

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: String){
        stringDelegate.setValue(thisRef, property, newValue)
    }
}

class NonEmptyStringDecorator(stringDelegate: StringDelegate): StringDecorator(stringDelegate){

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: String){
        if(newValue.isNotBlank()){
            super.stringDelegate.setValue(thisRef, property, newValue)
        }
    }
}

class EmailStringDecorator(stringDelegate: StringDelegate): StringDecorator(stringDelegate) {
    companion object{
        val emailRegex = Regex("^[A-Za-z](.*)([@])(.+)(\\.)(.+)")
    }

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: String){
        if(newValue.isNotBlank() && emailRegex.matches(newValue)){
            super.stringDelegate.setValue(thisRef, property, newValue)
        }
    }
}

class LoggingStringDecorator(stringDelegate: StringDelegate, private val log: MutableList<String>): StringDecorator(stringDelegate){
    override operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: String){
        val startValue: String = super.stringDelegate.getValue(thisRef, property)
        super.stringDelegate.setValue(thisRef, property, newValue)
        val finishValue = super.stringDelegate.getValue(thisRef, property)

        if(startValue != finishValue){
            log.add("Changing `${property.name}` from '$startValue' to '$finishValue'")
        }
    }
}