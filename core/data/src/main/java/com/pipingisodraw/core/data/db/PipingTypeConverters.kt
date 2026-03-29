package com.pipingisodraw.core.data.db

import androidx.room.TypeConverter
import com.pipingisodraw.core.domain.model.ComponentType
import com.pipingisodraw.core.domain.model.Orientation
import com.pipingisodraw.core.domain.model.PipingStandard
import com.pipingisodraw.core.domain.model.UnitSystem

class PipingTypeConverters {
    @TypeConverter
    fun toStandard(value: String): PipingStandard = PipingStandard.valueOf(value)

    @TypeConverter
    fun fromStandard(value: PipingStandard): String = value.name

    @TypeConverter
    fun toUnitSystem(value: String): UnitSystem = UnitSystem.valueOf(value)

    @TypeConverter
    fun fromUnitSystem(value: UnitSystem): String = value.name

    @TypeConverter
    fun toComponentType(value: String): ComponentType = ComponentType.valueOf(value)

    @TypeConverter
    fun fromComponentType(value: ComponentType): String = value.name

    @TypeConverter
    fun toOrientation(value: String): Orientation = Orientation.valueOf(value)

    @TypeConverter
    fun fromOrientation(value: Orientation): String = value.name
}
