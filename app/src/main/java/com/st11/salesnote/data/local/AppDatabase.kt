package com.st11.salesnote.data.local


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.st11.salesnote.model.SingleProductEntity
import com.st11.salesnote.model.SingleSaleEntity


@Database(entities = [SingleSaleEntity::class, SingleProductEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun singleSaleDao(): SingleSaleDao
    abstract fun singleSaleProductDao(): SingleSaleProductDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sales_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}