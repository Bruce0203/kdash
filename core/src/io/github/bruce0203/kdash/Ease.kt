package io.github.bruce0203.kdash

import com.soywiz.korma.interpolation.Easing

class Ease {

    private var ease = 1f
    private var easeDir = true
    private var easeSpeed = 0.01f
    val eased: Float get() {
        ease += easeSpeed * if (easeDir) 1 else -1
        if (ease >= 1 || ease <= 0.7) easeDir = !easeDir
        return Easing.EASE_IN_OUT_QUAD.invoke(ease.toDouble()).toFloat()
    }

}