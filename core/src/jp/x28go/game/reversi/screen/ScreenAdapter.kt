package jp.x28go.game.reversi.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import jp.x28go.game.reversi.Log
import jp.x28go.game.reversi.Main

/**
 * screen base class
 */

abstract class ScreenAdapter(game: Main) : Screen, Log {

    var game: Main
    lateinit var stage: Stage

    lateinit var skin: Skin

    init {
        log("base init")
        this.game = game
        initSkin()
    }

    abstract fun update(delta: Float)
    abstract fun draw(delta: Float)

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        update(delta)
        draw(delta)
    }

    override fun resize(width: Int, height: Int) {
        log("resize($width,$height)")
        stage.viewport.update(width, height, true)
    }

    override fun show() {
        log("show")
    }

    override fun hide() {
        log("hide")
        dispose()
    }

    override fun pause() {
        log("pause")
    }

    override fun resume() {
        log("resume")
    }

    override fun dispose() {
        this.log("dispose")
    }

    // initialize skin
    private fun initSkin() {
        skin = Skin(Gdx.files.internal("ui/uiskin.json"))
    }
}
