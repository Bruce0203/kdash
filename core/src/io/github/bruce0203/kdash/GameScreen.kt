package io.github.bruce0203.kdash

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Gdx.graphics
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.ScreenUtils
import java.io.File.separator
import kotlin.math.abs
import kotlin.math.max


open class GameScreen(private val name: String) : ScreenAdapter() {
    private val map by lazy { TmxMapLoader().load("stages$separator$name${separator}map.tmx") }
    private val renderer by lazy { OrthogonalTiledMapRenderer(map, tiled) }
    private val notePool by lazy {
        val layer = map.layers.getByType(TiledMapTileLayer::class.java)[0]
        val map = ArrayList<Pair<Cell, Float>>()
        for (x in 0 until layer.width) for (y in 0 until layer.height) {
            layer.getCell(x, y)?.apply {
                map.add(Pair(this, (layer.tileHeight * y).toFloat()))
            }
        }
        map
    }

    private val tiled by lazy { SpriteBatch() }
    private val batch by lazy { SpriteBatch() }
    private val overlay by lazy { SpriteBatch() }
    private val divAmount = map.properties["width"] as Int
    private val tileWidth = map.properties["tilewidth"] as Int
    private val cameraZoom = 10f
    private val camera by lazy {
        val width = (tileWidth * divAmount * cameraZoom).toFloat()
        OrthographicCamera(width, width)
    }
    private val auditLine by lazy { 10/16f * camera.viewportHeight }

    private val font30 = generateFont(30)
    private var cameraOffsetY = 0f
    private var speed = 1f
    private var acc = 0.1f
        set(value) { field = max(5f, value) }
    private var text = ""
    private var leftY = 0f

    init {
        Gdx.input.inputProcessor = Input { key ->


            false
        }
    }

    private fun centerCamera() {
        camera.position.set(camera.viewportWidth / cameraZoom*2, cameraOffsetY, 0f);
    }

    private fun cameraMovement() {
        cameraOffsetY += speed
        camera.position.y = cameraOffsetY
    }

    override fun show() {
        batch.projectionMatrix = camera.combined
        centerCamera()
    }

    override fun dispose() {
        tiled.dispose()
        font30.dispose()
        batch.dispose()
        map.dispose()
        renderer.dispose()
        overlay.dispose()
    }

    private fun drawGrid() {
        ShapeRenderer().apply {
            setAutoShapeType(true)
            begin()
            repeat(divAmount) { ind ->
                val x = graphics.width / divAmount.toFloat() * ind
                line(x, 0f, x, graphics.height.toFloat())
            }
            line(0f, auditLine, graphics.width.toFloat(), auditLine)
            line(100f, leftY, 100f, auditLine)
            end()
        }
    }

    private fun drawFpsOverlay() {
        overlay.begin()
        font30.draw(overlay, """
            |FPS: ${graphics.framesPerSecond}
            |$text
        """.trimMargin(), 0f, graphics.height.toFloat(), graphics.width.toFloat(), Align.center, false)
        overlay.end()
    }

    private fun noteAudit() {
        notePool.map { Pair(it, -((auditLine + cameraOffsetY) - it.second)) }.filter { it.second >= 0 }
            .sortedBy { it.second }.map { Pair(it.first.first, it.second)}
            .firstOrNull()?.let { pair ->
                text = pair.second.toString()
                ShapeRenderer().apply {
                    setAutoShapeType(true)
                    color = Color.GREEN
                    begin()
                    leftY = auditLine - pair.second
                    end()
                }

/*
                if (pair.second <= 20) {
                    pair.first.tile = null
                    notePool.removeIf { pair.first == it.first }
                }
*/
            }
    }


    override fun render(delta: Float) {
        ScreenUtils.clear(0f, 0f, 0f, 1f)
        cameraMovement()
        drawFpsOverlay()
        drawGrid()
        noteAudit()

        renderer.setView(camera)
        renderer.render()
        batch.begin()
        batch.end()
        camera.update()
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