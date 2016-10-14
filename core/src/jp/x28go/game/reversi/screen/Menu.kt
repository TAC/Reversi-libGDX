package jp.x28go.game.reversi.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import jp.x28go.game.reversi.Main

/**
 * menu screen
 */

class Menu(game: Main) :ScreenAdapter(game) {

    var stage: Stage

    init {
        log("menu init")

        stage = Stage(FitViewport(game.LOGICAL_WIDTH, game.LOGICAL_HEIGHT))
        Gdx.input.inputProcessor = stage

        val button = TextButton("START", skin, "default")
//        button.width = 400f
//        button.height = 80f
        log("width : " + Gdx.graphics.width.toString())
        log("height : " + Gdx.graphics.height.toString())
//        button.setPosition(Gdx.graphics.width / 2f, Gdx.graphics.height / 2f)
        button.setPosition(70f, 0f)
        button.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                game.setNextScreen(Board(game))
            }
        })

        stage.addActor(button)
    }

    override fun render(delta: Float) {
        super.render(delta)

        update(delta)
        draw(delta)
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
    }

    override fun update(delta: Float) {
    }

    override fun draw(delta: Float) {
        stage.draw()
    }
}