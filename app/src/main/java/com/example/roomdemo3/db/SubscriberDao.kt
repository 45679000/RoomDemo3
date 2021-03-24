package com.example.roomdemo3.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDao {

    //insert a single user
    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber):Long

    //updating subscriber
    @Update
    suspend fun updateSubscriber(subscriber: Subscriber)

    //deletes everything from the table
    @Query("DELETE FROM subscriber_data_table")
    suspend fun clear()

    @Delete
    suspend fun deleteAll(subscriber: Subscriber)

    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscribers(): LiveData<List<Subscriber>>
}