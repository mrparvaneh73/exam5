package com.example.exam5.di

import android.content.Context
import androidx.room.Room
import com.example.exam5.data.Local.UserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun dataBase(@ApplicationContext context:Context):UserDataBase=Room.databaseBuilder(context,
    UserDataBase::class.java,"user").fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun dao(db:UserDataBase)=db.userDao()
}