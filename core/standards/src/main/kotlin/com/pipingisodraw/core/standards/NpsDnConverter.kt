package com.pipingisodraw.core.standards

object NpsDnConverter {
    private val npsToDn = mapOf(
        "1/2" to 15,
        "3/4" to 20,
        "1" to 25,
        "1-1/2" to 40,
        "2" to 50,
        "3" to 80,
        "4" to 100,
        "6" to 150,
        "8" to 200,
        "10" to 250,
        "12" to 300,
        "16" to 400,
        "20" to 500,
        "24" to 600
    )

    private val dnToNps = npsToDn.entries.associate { (nps, dn) -> dn to nps }

    fun toDn(nps: String): Int? = npsToDn[nps]

    fun toNps(dn: Int): String? = dnToNps[dn]

    fun requireDn(nps: String): Int =
        requireNotNull(toDn(nps)) { "NPS não suportado: $nps" }

    fun requireNps(dn: Int): String =
        requireNotNull(toNps(dn)) { "DN não suportado: $dn" }

    fun supportedPairs(): Map<String, Int> = npsToDn.toMap()
}
