package com.seng440.ajl190.huttrackr.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.seng440.ajl190.huttrackr.data.dao.HutDao
import com.seng440.ajl190.huttrackr.data.model.HutResponse

@Database(entities = [HutResponse::class], version = 1, exportSchema = true)
abstract class DocDatabase : RoomDatabase() {

    abstract fun hutDao(): HutDao

    companion object {

        @Volatile private var instance: DocDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room
            .databaseBuilder(context.applicationContext,
                DocDatabase::class.java,
                "doc.db")
            .build()
    }
}