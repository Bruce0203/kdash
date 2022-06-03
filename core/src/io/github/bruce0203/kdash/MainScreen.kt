package io.github.bruce0203.kdash

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.ScreenUtils

class MainScreen : ScreenAdapter() {

    val ease = Ease()

    override fun render(delta: Float) {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        batch.begin()
        val font = BitmapFont()
        val width = Gdx.graphics.width.toFloat()
        val height = Gdx.graphics.height.toFloat()
        font.data.setScale(5f)
        font.draw(batch, "KDash", 0f, height*13/16, width, Align.center, true)
        font.data.setScale(2f * ease.eased)
        font.draw(batch, ">> press any key to start playing <<", 0f, height*10/16, width, Align.center, true)
        batch.end()
        Gdx.input.inputProcessor = Input {
            screen = GameScreen()
            false
        }
    }

    override fun dispose() { batch.dispose() }
}