package com.pipingisodraw.feature.drawing

import com.pipingisodraw.core.domain.model.IsoPoint
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

data class ScreenPoint(val x: Float, val y: Float)

object IsoProjection {
    private val cos30 = cos(PI / 6).toFloat()
    private val sin30 = sin(PI / 6).toFloat()

    fun isoToScreen(point: IsoPoint, cellSize: Float, origin: ScreenPoint): ScreenPoint =
        isoToScreen(point.gx, point.gy, point.gz, cellSize, origin)

    fun isoToScreen(gx: Int, gy: Int, gz: Int, cellSize: Float, origin: ScreenPoint): ScreenPoint {
        val sx = (gx - gy) * cellSize * cos30
        val sy = (gx + gy) * cellSize * sin30 - gz * cellSize
        return ScreenPoint(origin.x + sx, origin.y + sy)
    }

    fun screenToIso(screen: ScreenPoint, cellSize: Float, origin: ScreenPoint, gz: Int): IsoPoint {
        val dx = (screen.x - origin.x) / (cellSize * cos30)
        val dy = (screen.y - origin.y + gz * cellSize) / (cellSize * sin30)
        val gx = ((dx + dy) / 2f).roundToInt()
        val gy = ((dy - dx) / 2f).roundToInt()
        return IsoPoint(gx, gy, gz)
    }
}
