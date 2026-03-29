package com.pipingisodraw.feature.drawing

import com.pipingisodraw.core.domain.model.IsoPoint
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class IsoProjectionTest {
    @Test
    fun `isoToScreen e screenToIso sao inversas para 50 pontos de grid`() {
        val cellSize = 12.5f
        val origin = ScreenPoint(200f, 180f)
        val points = buildList {
            for (gz in 0..1) {
                for (gx in 0..4) {
                    for (gy in 0..4) {
                        add(IsoPoint(gx, gy, gz))
                    }
                }
            }
        }

        assertEquals(50, points.size)

        points.forEach { point ->
            val screen = IsoProjection.isoToScreen(point, cellSize, origin)
            val roundTrip = IsoProjection.screenToIso(screen, cellSize, origin, point.gz)
            assertEquals(point, roundTrip)
        }
    }
}
