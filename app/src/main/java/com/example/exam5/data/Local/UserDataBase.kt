package com.example.exam5.data.Local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.exam5.model.User
import com.example.exam5.utils.Converters

@Database(entities = [User::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class UserDataBase :RoomDatabase() {
    abstract fun userDao():UserDao
}