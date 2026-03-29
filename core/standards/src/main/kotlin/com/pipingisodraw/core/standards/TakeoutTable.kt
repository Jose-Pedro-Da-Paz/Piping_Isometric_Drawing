package com.pipingisodraw.core.standards

object TakeoutTable {
    private val elbow90LRByDnMm = mapOf(
        25 to 38,
        40 to 57,
        50 to 76,
        80 to 114,
        100 to 152,
        150 to 229,
        200 to 305,
        250 to 381,
        300 to 457
    )

    fun elbow90LongRadiusMm(dn: Int): Int? = elbow90LRByDnMm[dn]

    fun requireElbow90LongRadiusMm(dn: Int): Int =
        requireNotNull(elbow90LongRadiusMm(dn)) { "DN não suportado para cotovelo 90° LR: $dn" }

    fun supportedElbow90LongRadius(): Map<Int, Int> = elbow90LRByDnMm.toMap()
}
