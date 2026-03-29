package com.pipingisodraw.core.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects ORDER BY name")
    fun getProjects(): List<ProjectEntity>

    @Query("SELECT * FROM projects WHERE id = :id")
    fun getProject(id: String): ProjectEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(project: ProjectEntity)

    @Query("DELETE FROM projects WHERE id = :id")
    fun delete(id: String)
}
