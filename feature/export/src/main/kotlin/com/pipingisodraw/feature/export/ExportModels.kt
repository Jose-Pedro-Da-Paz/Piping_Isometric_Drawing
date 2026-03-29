package com.pipingisodraw.feature.export

import com.pipingisodraw.core.domain.model.Project

enum class ExportFormat {
    PDF,
    DXF,
    PNG,
    SVG,
    CSV
}

data class ExportRequest(
    val project: Project,
    val format: ExportFormat
)

data class ExportResult(
    val success: Boolean,
    val message: String
)

interface ExportService {
    fun export(request: ExportRequest): ExportResult
}
