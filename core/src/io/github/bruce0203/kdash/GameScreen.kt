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

    val batch by lazy { SpriteBatch() }
    val overlay by lazy { SpriteBatch() }
    val grid by lazy { SpriteBatch() }
    private val camera by lazy { OrthographicCamera(50f, 50f) }

    val font10 = generateFont(100)

    init {
        Gdx.input.inputProcessor = Input{false}
    }

    fun centerCamera() {
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
    }

    fun make() {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        centerCamera()
        drawGrid()
    }

    override fun show() {
        val sr = ShapeRenderer()
        batch.setProjectionMatrix(camera.combined);
        sr.color = Color.WHITE
        sr.projectionMatrix = camera.combined
        make()
    }

    override fun dispose() {
        font10.dispose()
        grid.dispose()
        overlay.dispose()
    }

    private fun drawGrid() {
        grid.begin()
        ShapeRenderer().apply {
            setAutoShapeType(true)
            begin()
            color = Color.GREEN
            line(10f, 0f, 10f, graphics.height.toFloat())
            end()
        }
        grid.end()
    }

    override fun render(delta: Float) {
        val width = Gdx.graphics.width.toFloat()
        val height = Gdx.graphics.height.toFloat()
        overlay.begin()
        font10.draw(overlay, "FPS: ${Gdx.graphics.framesPerSecond}", 0f, 100f, width, Align.center, true)
        overlay.end()
    }

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
        make()
    }

    override fun resume() {
        super.resume()
        make()
    }
}