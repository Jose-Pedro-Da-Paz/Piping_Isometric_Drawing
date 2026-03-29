package com.pipingisodraw.core.domain.model

import java.util.UUID

enum class PipingStandard {
    ASME,
    ISO,
    MIXED
}

enum class UnitSystem {
    IMPERIAL,
    METRIC
}

enum class ComponentType {
    PIPE_RUN,
    ELBOW_90,
    ELBOW_45,
    TEE_EQUAL,
    TEE_REDUCING,
    REDUCER_CONCENTRIC,
    REDUCER_ECCENTRIC,
    CAP,
    FLANGE_WN,
    FLANGE_SO,
    FLANGE_BLIND,
    VALVE_GATE,
    VALVE_GLOBE,
    VALVE_BUTTERFLY,
    VALVE_CHECK,
    VALVE_BALL
}

enum class Orientation {
    X_POSITIVE,
    X_NEGATIVE,
    Y_POSITIVE,
    Y_NEGATIVE,
    Z_POSITIVE,
    Z_NEGATIVE
}

data class IsoPoint(val gx: Int, val gy: Int, val gz: Int)

data class PipeSpec(
    val dn: Int,
    val schedule: String,
    val material: String,
    val rating: String
)

data class PlacedComponent(
    val id: UUID,
    val type: ComponentType,
    val position: IsoPoint,
    val orientation: Orientation,
    val spec: PipeSpec,
    val tag: String?
)

data class Project(
    val id: UUID,
    val name: String,
    val standard: PipingStandard,
    val units: UnitSystem,
    val lineNumber: String,
    val fluidService: String,
    val components: List<PlacedComponent>
)
