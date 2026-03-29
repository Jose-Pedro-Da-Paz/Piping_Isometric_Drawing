package com.pipingisodraw.core.standards

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NpsDnConverterTest {
    @Test
    fun `converte tabela completa de NPS para DN e vice-versa`() {
        val pairs = NpsDnConverter.supportedPairs()
        assertEquals(14, pairs.size)

        pairs.forEach { (nps, dn) ->
            assertEquals(dn, NpsDnConverter.requireDn(nps))
            assertEquals(nps, NpsDnConverter.requireNps(dn))
        }
    }
}
