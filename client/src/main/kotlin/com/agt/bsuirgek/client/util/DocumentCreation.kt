package com.agt.bsuirgek.client.util

import java.math.BigInteger

const val CONST = 74

val BigInteger.pix
    get() = this.toDouble()/1440 * CONST

val Int.pix
    get() = this.toDouble()/1440 * CONST

