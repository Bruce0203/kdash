package io.github.bruce0203.kdash

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen

var screen: Screen = MainScreen()
    set(value) {
        field.dispose()
        field.hide()
        field = value
        field.show()
    }

class Main : ApplicationAdapter() {
    override fun create() { screen.show() }
    override fun resize(width: Int, height: Int) { screen.resize(width, height) }
    override fun pause() { screen.pause() }
    override fun resume() { screen.resize(Gdx.graphics.width, Gdx.graphics.height) }
    override fun dispose() { screen.dispose() }
    override fun render() { screen.render(Gdx.graphics.deltaTime) }
}
