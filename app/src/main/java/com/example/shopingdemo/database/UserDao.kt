package com.example.shopingdemo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = IGNORE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun getUserByEmail(email: String): User

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    suspend fun getUser(email: String, password: String): User?

    @Query("SELECT COUNT(*) FROM user")
    suspend fun getCount(): Int
}
