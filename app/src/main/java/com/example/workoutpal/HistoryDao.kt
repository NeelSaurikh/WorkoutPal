package com.example.workoutpal

import androidx.room.Dao
import androidx.room.Insert


@Dao
interface HistoryDao {

    @Insert
    suspend fun  insert(historyEntity: HistoryEntity)
}