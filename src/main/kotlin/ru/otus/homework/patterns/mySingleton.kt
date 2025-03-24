package ru.otus.homework.patterns

import java.io.File

fun main() {
    val cf = ConfigFile.instance
    cf.setMap(mutableMapOf("par1" to "111", "par2" to "222", "par3" to "333"))
    cf.save()
    cf.setMap(mutableMapOf())
    cf.load()
    val mp = cf.getMap()
    print(mp)
}

class ConfigFile private constructor() {
    companion object {
        var map1 = mutableMapOf<String, String>()
        val instance : ConfigFile by lazy {
            ConfigFile()
        }
    }
    fun load() {
        var readKey = true
        var key = ""
        for (line in File("test.txt").readLines()) {
            if (readKey) {
                key = line
                readKey = false
            } else {
                map1[key] = line
                readKey = true
            }
        }
    }
    fun save() {
        val writer = File("test.txt").bufferedWriter()
        for(elem in map1) {
            writer.write(elem.key + "\n")
            writer.write(elem.value + "\n")
        }
        writer.close()
    }
    fun getMap() :  MutableMap<String, String> {
        return map1
    }
    fun setMap(mp : MutableMap<String, String>) {
        map1 = mp
    }
}