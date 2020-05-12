package com.seng440.ajl190.huttrackr.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.seng440.ajl190.huttrackr.data.dao.HutDao
import com.seng440.ajl190.huttrackr.data.dao.TrackDao
import com.seng440.ajl190.huttrackr.data.dao.WishDao
import com.seng440.ajl190.huttrackr.data.model.HutResponse
import com.seng440.ajl190.huttrackr.data.model.TrackResponse
import com.seng440.ajl190.huttrackr.data.model.WishItem

@Database(entities = [HutResponse::class, TrackResponse::class, WishItem::class], version = 9, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DocDatabase : RoomDatabase() {

    abstract fun hutDao(): HutDao
    abstract fun trackDao(): TrackDao
    abstract fun wishHutItemDao(): WishDao

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
            .fallbackToDestructiveMigration()
            .build()
    }
}