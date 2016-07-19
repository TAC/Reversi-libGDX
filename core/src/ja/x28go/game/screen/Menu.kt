package ja.x28go.game.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import ja.x28go.game.Main

/**
 * menu screen
 */

class Menu(game: Main) : ScreenAdapter(game) {

    lateinit var stage: Stage
    lateinit var batch: SpriteBatch

    init {
        log("menu init")

        stage = Stage(FitViewport(game.LOGICAL_WIDTH, game.LOGICAL_HEIGHT))
        stage = Stage()
        Gdx.input.inputProcessor = stage

        batch = SpriteBatch()
        batch.projectionMatrix = stage.viewport.camera.combined

        val button = TextButton("START", skin, "default")
        button.width = 400f
        button.height = 80f
        button.setPosition(Gdx.graphics.width / 2 - 200f, Gdx.graphics.height / 2 - 40f)
        button.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                button.setText("CLICKED")
            }
        })

        stage.addActor(button)
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