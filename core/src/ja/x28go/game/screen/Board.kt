package ja.x28go.game.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import ja.x28go.game.Main

/**
 * board screen
 */

class Board(game: Main) : ScreenAdapter(game) {

    lateinit var stage: Stage
    lateinit var batch: SpriteBatch

    init {
        log("board init")

        stage = Stage(FitViewport(game.LOGICAL_WIDTH, game.LOGICAL_HEIGHT))
        stage = Stage()
        Gdx.input.inputProcessor = stage

        batch = SpriteBatch()
        batch.projectionMatrix = stage.viewport.camera.combined


//        stage.addActor(button)
    }

    override fun render(delta: Float) {
        super.render(delta)

        batch.begin()
        update(delta)
        draw(delta)
        batch.end()
    }

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
        stage.viewport.update(width, height, true)
    }

    override fun show() {
        super.show()
    }

    override fun hide() {
        super.hide()
    }

    override fun pause() {
        super.pause()
    }

    override fun resume() {
        super.resume()
    }

    override fun dispose() {
        super.dispose()
        batch.dispose()
    }

    override fun update(delta: Float) {
    }

    override fun draw(delta: Float) {
        stage.draw()
    }
}