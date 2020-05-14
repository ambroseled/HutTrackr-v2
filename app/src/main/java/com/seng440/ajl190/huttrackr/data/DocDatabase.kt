package com.seng440.ajl190.huttrackr.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.seng440.ajl190.huttrackr.data.dao.*
import com.seng440.ajl190.huttrackr.data.model.*

@Database(entities = [HutResponse::class, TrackResponse::class, WishItem::class, VisitItem::class, LastFetch::class], version = 15, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DocDatabase : RoomDatabase() {

    abstract fun hutDao(): HutDao
    abstract fun trackDao(): TrackDao
    abstract fun wishHutItemDao(): WishDao
    abstract fun visitItemDao(): VisitDao
    abstract fun lastFectDao(): LastFetchDao

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