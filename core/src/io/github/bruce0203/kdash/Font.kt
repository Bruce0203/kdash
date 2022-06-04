package io.github.bruce0203.kdash

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import java.io.File.*

val generator = FreeTypeFontGenerator(Gdx.files.internal("fonts${separator}NotoSansKR-Regular.otf"))

fun generateFont(size: Int): BitmapFont {
    val parm = FreeTypeFontGenerator.FreeTypeFontParameter().apply { this.size = size }
    return generator.generateFont(parm)!!
}
