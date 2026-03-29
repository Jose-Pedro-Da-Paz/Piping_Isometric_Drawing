package com.pipingisodraw.core.standards

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TakeoutTableTest {
    @Test
    fun `valores de take-out para cotovelo 90 LR seguem tabela ASME B16_9`() {
        val table = TakeoutTable.supportedElbow90LongRadius()
        assertEquals(9, table.size)

        assertEquals(38, TakeoutTable.requireElbow90LongRadiusMm(25))
        assertEquals(57, TakeoutTable.requireElbow90LongRadiusMm(40))
        assertEquals(76, TakeoutTable.requireElbow90LongRadiusMm(50))
        assertEquals(114, TakeoutTable.requireElbow90LongRadiusMm(80))
        assertEquals(152, TakeoutTable.requireElbow90LongRadiusMm(100))
        assertEquals(229, TakeoutTable.requireElbow90LongRadiusMm(150))
        assertEquals(305, TakeoutTable.requireElbow90LongRadiusMm(200))
        assertEquals(381, TakeoutTable.requireElbow90LongRadiusMm(250))
        assertEquals(457, TakeoutTable.requireElbow90LongRadiusMm(300))
    }
}
