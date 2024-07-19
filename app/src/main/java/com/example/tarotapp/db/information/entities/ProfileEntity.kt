package com.example.tarotapp.db.information.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date
import java.util.UUID

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val nickname: String,
    @ColumnInfo(name = "date_start") val dateStart: LocalDate,
    @ColumnInfo(name = "date_birth") val dateBirth: LocalDate,
    @ColumnInfo(name = "uri_picture") val uriPicture: String?
)