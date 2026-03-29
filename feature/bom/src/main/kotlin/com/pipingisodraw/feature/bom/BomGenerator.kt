package com.pipingisodraw.feature.bom

import com.pipingisodraw.core.domain.model.ComponentType
import com.pipingisodraw.core.domain.model.PipeSpec
import com.pipingisodraw.core.domain.model.PlacedComponent
import com.pipingisodraw.core.domain.model.PipingStandard


data class BomItem(
    val item: Int,
    val quantity: Int,
    val description: String,
    val size: String,
    val material: String,
    val standard: PipingStandard,
    val unitWeightKg: Double,
    val totalWeightKg: Double
)

object BomGenerator {
    fun generate(components: List<PlacedComponent>, standard: PipingStandard): List<BomItem> {
        if (components.isEmpty()) return emptyList()

        val grouped = components.groupBy { it.type to it.spec }
        return grouped.entries.mapIndexed { index, (key, items) ->
            val (type, spec) = key
            val quantity = items.size
            val description = descriptionFor(type)
            val size = sizeFor(spec)
            val unitWeight = 0.0
            BomItem(
                item = index + 1,
                quantity = quantity,
                description = description,
                size = size,
                material = spec.material,
                standard = standard,
                unitWeightKg = unitWeight,
                totalWeightKg = unitWeight * quantity
            )
        }
    }

    private fun descriptionFor(type: ComponentType): String = when (type) {
        ComponentType.PIPE_RUN -> "PIPE RUN"
        ComponentType.ELBOW_90 -> "ELBOW 90°"
        ComponentType.ELBOW_45 -> "ELBOW 45°"
        ComponentType.TEE_EQUAL -> "EQUAL TEE"
        ComponentType.TEE_REDUCING -> "REDUCING TEE"
        ComponentType.REDUCER_CONCENTRIC -> "CONCENTRIC REDUCER"
        ComponentType.REDUCER_ECCENTRIC -> "ECCENTRIC REDUCER"
        ComponentType.CAP -> "CAP"
        ComponentType.FLANGE_WN -> "FLANGE WN"
        ComponentType.FLANGE_SO -> "FLANGE SO"
        ComponentType.FLANGE_BLIND -> "BLIND FLANGE"
        ComponentType.VALVE_GATE -> "GATE VALVE"
        ComponentType.VALVE_GLOBE -> "GLOBE VALVE"
        ComponentType.VALVE_BUTTERFLY -> "BUTTERFLY VALVE"
        ComponentType.VALVE_CHECK -> "CHECK VALVE"
        ComponentType.VALVE_BALL -> "BALL VALVE"
    }

    private fun sizeFor(spec: PipeSpec): String = "DN ${spec.dn} ${spec.schedule}"
}
