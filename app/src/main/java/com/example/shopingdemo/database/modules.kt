package com.example.shopingdemo.database

import android.app.Application
import androidx.room.Room
import com.example.shopingdemo.auth.ui.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


fun provideDatabase(application: Application): UserDatabase =
    Room.databaseBuilder(application, UserDatabase::class.java, "user_database")
        .fallbackToDestructiveMigration().build()

fun provideDao(db: UserDatabase) = db.userDao()

val dbModule = module {
    single {
        provideDatabase(get())
    }

    single {
        provideDao(get())
    }

    viewModel {
        SignInViewModel(get(), get())
    }

}