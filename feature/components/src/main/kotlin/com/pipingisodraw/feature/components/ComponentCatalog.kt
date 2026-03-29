package com.pipingisodraw.feature.components

import com.pipingisodraw.core.domain.model.ComponentType

data class ComponentDefinition(
    val type: ComponentType,
    val ports: Int,
    val label: String
)

object ComponentCatalog {
    val phase1Components: List<ComponentDefinition> = listOf(
        ComponentDefinition(ComponentType.PIPE_RUN, 2, "Tubo reto"),
        ComponentDefinition(ComponentType.ELBOW_90, 2, "Cotovelo 90°"),
        ComponentDefinition(ComponentType.ELBOW_45, 2, "Cotovelo 45°"),
        ComponentDefinition(ComponentType.TEE_EQUAL, 3, "Tê igual"),
        ComponentDefinition(ComponentType.TEE_REDUCING, 3, "Tê reduzido"),
        ComponentDefinition(ComponentType.REDUCER_CONCENTRIC, 2, "Redução concêntrica"),
        ComponentDefinition(ComponentType.REDUCER_ECCENTRIC, 2, "Redução excêntrica"),
        ComponentDefinition(ComponentType.CAP, 1, "Tampa"),
        ComponentDefinition(ComponentType.FLANGE_WN, 2, "Flange WN"),
        ComponentDefinition(ComponentType.FLANGE_SO, 2, "Flange SO"),
        ComponentDefinition(ComponentType.FLANGE_BLIND, 1, "Flange cega"),
        ComponentDefinition(ComponentType.VALVE_GATE, 2, "Válvula gaveta"),
        ComponentDefinition(ComponentType.VALVE_GLOBE, 2, "Válvula globo"),
        ComponentDefinition(ComponentType.VALVE_BUTTERFLY, 2, "Válvula borboleta"),
        ComponentDefinition(ComponentType.VALVE_CHECK, 2, "Válvula de retenção"),
        ComponentDefinition(ComponentType.VALVE_BALL, 2, "Válvula esfera")
    )
}
