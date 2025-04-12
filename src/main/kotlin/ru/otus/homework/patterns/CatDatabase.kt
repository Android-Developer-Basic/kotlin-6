package ru.otus.homework.patterns

class CatDatabase {

    companion object {
        private var INSTANCE: CatDatabase? = null

        fun newInstance(): CatDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            val instance = CatDatabase()
            INSTANCE = instance
            return instance
        }
    }
}