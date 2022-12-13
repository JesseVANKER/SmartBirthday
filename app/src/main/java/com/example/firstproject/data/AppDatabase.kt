package com.example.firstproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firstproject.model.Personne

@Database(entities = [Personne::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personneDao() : PersonneDAO

    companion object {
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context:Context) : AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "personne_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}