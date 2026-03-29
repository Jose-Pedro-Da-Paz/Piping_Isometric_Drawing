package com.pipingisodraw.core.data.repository

import com.pipingisodraw.core.data.db.PlacedComponentDao
import com.pipingisodraw.core.data.db.PlacedComponentEntity
import com.pipingisodraw.core.data.db.ProjectDao
import com.pipingisodraw.core.data.db.ProjectEntity
import com.pipingisodraw.core.domain.model.IsoPoint
import com.pipingisodraw.core.domain.model.PipeSpec
import com.pipingisodraw.core.domain.model.PlacedComponent
import com.pipingisodraw.core.domain.model.Project
import com.pipingisodraw.core.domain.repository.ProjectRepository
import java.util.UUID

class ProjectRepositoryImpl(
    private val projectDao: ProjectDao,
    private val componentDao: PlacedComponentDao
) : ProjectRepository {
    override fun getProjects(): List<Project> =
        projectDao.getProjects().map { entity ->
            val components = componentDao.getComponentsForProject(entity.id)
            entity.toDomain(components)
        }

    override fun getProject(id: UUID): Project? {
        val entity = projectDao.getProject(id.toString()) ?: return null
        val components = componentDao.getComponentsForProject(entity.id)
        return entity.toDomain(components)
    }

    override fun saveProject(project: Project) {
        projectDao.upsert(project.toEntity())
        componentDao.deleteByProjectId(project.id.toString())
        componentDao.insertAll(project.components.map { it.toEntity(project.id) })
    }

    override fun deleteProject(id: UUID) {
        componentDao.deleteByProjectId(id.toString())
        projectDao.delete(id.toString())
    }
}

private fun ProjectEntity.toDomain(components: List<PlacedComponentEntity>): Project = Project(
    id = UUID.fromString(id),
    name = name,
    standard = standard,
    units = units,
    lineNumber = lineNumber,
    fluidService = fluidService,
    components = components.map { it.toDomain() }
)

private fun Project.toEntity(): ProjectEntity = ProjectEntity(
    id = id.toString(),
    name = name,
    standard = standard,
    units = units,
    lineNumber = lineNumber,
    fluidService = fluidService
)

private fun PlacedComponentEntity.toDomain(): PlacedComponent = PlacedComponent(
    id = UUID.fromString(id),
    type = type,
    position = IsoPoint(gx, gy, gz),
    orientation = orientation,
    spec = PipeSpec(dn = dn, schedule = schedule, material = material, rating = rating),
    tag = tag
)

private fun PlacedComponent.toEntity(projectId: UUID): PlacedComponentEntity = PlacedComponentEntity(
    id = id.toString(),
    projectId = projectId.toString(),
    type = type,
    gx = position.gx,
    gy = position.gy,
    gz = position.gz,
    orientation = orientation,
    dn = spec.dn,
    schedule = spec.schedule,
    material = spec.material,
    rating = spec.rating,
    tag = tag
)
