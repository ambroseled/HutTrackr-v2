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

        @Volatile
        private var INSTANCE: DocDatabase? = null

        fun getDatabase(context: Context): DocDatabase? {
            if (INSTANCE == null) {
                synchronized(DocDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            DocDatabase::class.java,
                            "doc_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}