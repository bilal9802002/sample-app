package com.interview.sample.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.interview.sample.data.db.dao.PatientDao
import com.interview.sample.data.db.dao.UserDao
import com.interview.sample.domain.entities.PatientEntity
import com.interview.sample.domain.entities.ProblemEntityTypeConverter
import com.interview.sample.domain.entities.UserEntity

@Database(entities = [UserEntity::class, PatientEntity::class],
    version = 1, exportSchema = false)
@TypeConverters(ProblemEntityTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun patientDao(): PatientDao

    companion object {
        private val DB_NAME = "app-db"

        fun create(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
    }
}