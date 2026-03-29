package com.pipingisodraw.core.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pipingisodraw.core.domain.model.ComponentType
import com.pipingisodraw.core.domain.model.Orientation
import com.pipingisodraw.core.domain.model.PipingStandard
import com.pipingisodraw.core.domain.model.UnitSystem

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val standard: PipingStandard,
    val units: UnitSystem,
    val lineNumber: String,
    val fluidService: String
)

@Entity(tableName = "placed_components")
data class PlacedComponentEntity(
    @PrimaryKey
    val id: String,
    val projectId: String,
    val type: ComponentType,
    val gx: Int,
    val gy: Int,
    val gz: Int,
    val orientation: Orientation,
    val dn: Int,
    val schedule: String,
    val material: String,
    val rating: String,
    val tag: String?
)
