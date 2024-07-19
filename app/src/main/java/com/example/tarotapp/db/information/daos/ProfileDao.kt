package com.example.tarotapp.db.information.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tarotapp.db.information.entities.ProfileEntity

@Dao
interface ProfileDao {
    @Insert
    fun insert(entity: ProfileEntity)

    @Delete
    fun delete(entity: ProfileEntity)

    @Query("SELECT * FROM profile")
    fun findAll(): List<ProfileEntity>?
}