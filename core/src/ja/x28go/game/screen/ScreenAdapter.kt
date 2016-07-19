package ja.x28go.game.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import ja.x28go.game.Log
import ja.x28go.game.Main

/**
 * screen base class
 */

abstract class ScreenAdapter(game: Main) : Screen, Log {

    var game: Main

    lateinit var skin: Skin

    init {
        log("base init")
        this.game = game
        initSkin()
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    override fun resize(width: Int, height: Int) {
        log("resize($width,$height)")
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

    abstract fun update(delta: Float)
    abstract fun draw(delta: Float)

    // initialize skin
    private fun initSkin() {
        skin = Skin(Gdx.files.internal("ui/uiskin.json"))
    }
}
