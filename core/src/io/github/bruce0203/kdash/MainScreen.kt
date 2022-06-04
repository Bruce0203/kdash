package io.github.bruce0203.kdash

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.ScreenUtils

class MainScreen : ScreenAdapter() {

    val batch by lazy { SpriteBatch() }
    val font100 = generateFont(100)
    val font25 = generateFont(25)

    override fun render(delta: Float) {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        batch.begin()
        val width = Gdx.graphics.width.toFloat()
        val height = Gdx.graphics.height.toFloat()
        font100.draw(batch, "KDash", 0f, height*13/16, width, Align.center, true)
        font25.draw(batch, ">> press any key to start playing <<", 0f, height*10/16, width, Align.center, true)
        batch.end()
        Gdx.input.inputProcessor = Input {
            screen = GameScreen()
            false
        }
    }

    override fun dispose() {
        batch.dispose()
        font100.dispose()
        font25.dispose()
    }
}