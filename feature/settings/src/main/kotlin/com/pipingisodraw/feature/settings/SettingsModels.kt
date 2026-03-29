package com.pipingisodraw.feature.settings

import com.pipingisodraw.core.domain.model.PipingStandard
import com.pipingisodraw.core.domain.model.UnitSystem

data class TitleBlockSettings(
    val projectName: String,
    val lineNumber: String,
    val fluidService: String,
    val designPressure: String,
    val designTemperature: String,
    val pipingClass: String,
    val revision: String,
    val date: String,
    val drawnBy: String,
    val checkedBy: String,
    val approvedBy: String,
    val documentNumber: String
)

data class ProjectSettings(
    val standard: PipingStandard,
    val units: UnitSystem,
    val northAngleDegrees: Float,
    val titleBlock: TitleBlockSettings
)
