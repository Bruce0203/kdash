package io.github.bruce0203.kdash

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Gdx.graphics
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.StretchViewport


open class GameScreen : ScreenAdapter() {

    val overlay by lazy { SpriteBatch() }
    val grid by lazy { SpriteBatch() }
    private val camera by lazy { OrthographicCamera(50f, 50f) }

    fun centerCamera() {
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
    }

    override fun show() {
        val sr = ShapeRenderer()
        batch.setProjectionMatrix(camera.combined);
        sr.color = Color.WHITE
        sr.projectionMatrix = camera.combined
        centerCamera()
    }

    override fun render(delta: Float) {

        ScreenUtils.clear(0f, 0f, 0f, 1f)
        grid.begin()
        ShapeRenderer().apply {
            setAutoShapeType(true)
            begin()
            color = Color.GREEN
            line(10f, 0f, 10f, graphics.height.toFloat())
            end()
        }
        val font = BitmapFont()
        val width = Gdx.graphics.width.toFloat()
        val height = Gdx.graphics.height.toFloat()
        font.data.setScale(2f)
        overlay.begin()
        font.draw(overlay, "FPS: ${Gdx.graphics.framesPerSecond}", 0f, 100f, width, Align.center, true)
        overlay.end()
        grid.end()
    }

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
        centerCamera()
    }

    override fun resume() {
        super.resume()
        centerCamera()
    }
}