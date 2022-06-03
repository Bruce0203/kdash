package io.github.bruce0203.kdash

import com.badlogic.gdx.InputProcessor

class Input(val block: (Int) -> Boolean) : InputProcessor {

    override fun keyUp(keycode: Int): Boolean = block(keycode)

    override fun keyDown(keycode: Int): Boolean = false

    override fun keyTyped(character: Char): Boolean = false

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = false

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean = false

    override fun scrolled(amountX: Float, amountY: Float): Boolean = false
}