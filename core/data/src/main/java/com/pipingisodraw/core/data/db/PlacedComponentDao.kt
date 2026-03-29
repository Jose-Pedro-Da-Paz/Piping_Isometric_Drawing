package com.pipingisodraw.core.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlacedComponentDao {
    @Query("SELECT * FROM placed_components WHERE projectId = :projectId")
    fun getComponentsForProject(projectId: String): List<PlacedComponentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(components: List<PlacedComponentEntity>)

    @Query("DELETE FROM placed_components WHERE projectId = :projectId")
    fun deleteByProjectId(projectId: String)
}
