package com.pipingisodraw.core.domain.repository

import com.pipingisodraw.core.domain.model.Project
import java.util.UUID

interface ProjectRepository {
    fun getProjects(): List<Project>

    fun getProject(id: UUID): Project?

    fun saveProject(project: Project)

    fun deleteProject(id: UUID)
}
