package com.example.roomdemo3.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriber_data_table")
data class Subscriber (

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "Subscriber_name")
        var id : Int,

        @ColumnInfo(name = "Subscriber_id")
        var name : String,

        @ColumnInfo(name = "Subscriber_email")
        var email : String


)