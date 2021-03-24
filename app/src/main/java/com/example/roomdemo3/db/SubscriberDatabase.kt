package com.example.roomdemo3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class], version = 1, exportSchema = false)
abstract class SubscriberDatabase : RoomDatabase() {

    // declare an abstract value of type SubscriberDao
    abstract val subscriberDao : SubscriberDao

    // Declare a companion object
    companion object{
        @Volatile
        private var INSTANCE: SubscriberDatabase? = null

        // Declare a @volatile INSTANCE variable
        fun getInstance(context: Context) : SubscriberDatabase {
            synchronized (this){
                var instance = INSTANCE

                if (instance ==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_data_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}