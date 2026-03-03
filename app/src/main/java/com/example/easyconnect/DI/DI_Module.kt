package com.example.easyconnect.DI

import android.app.Application
import androidx.room.Room
import com.example.easyconnect.DATABASE_NAME
import com.example.easyconnect.Data.AppDatabase
import com.example.easyconnect.Data.Repositories.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DI_Module {

    @Provides
    @Singleton
   fun provideContactDatabase(application: Application): AppDatabase {
      return Room.databaseBuilder(application, AppDatabase::class.java,
          DATABASE_NAME)
          .fallbackToDestructiveMigration()
          .build()
  }
   @Provides
   @Singleton
   fun provideContactRepository(database: AppDatabase): Repository{
       return Repository(database)
  }
}