package jp.x28go.game.reversi.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
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

        val buttonStart = TextButton("START", skin, "default")

        val buttonConnect = TextButton(" C ", skin, "default")
        val buttonDisconnect = TextButton(" D ", skin, "default")
        val buttonSend = TextButton(" S ", skin, "default")
        val inputOtherPeerId = TextField("other peer id", skin, "default")
        val inputMessage = TextField("input message", skin, "default")
        val labelMessage = Label("message", skin, "default")

//        buttonStart.width = 400f
//        buttonStart.height = 80f
        log("width : " + Gdx.graphics.width.toString())
        log("height : " + Gdx.graphics.height.toString())
//        buttonStart.setPosition(Gdx.graphics.width / 2f, Gdx.graphics.height / 2f)
        buttonStart.setPosition(70f, 0f)

        buttonConnect.setPosition(152f, 102f)
        buttonDisconnect.setPosition(70f, 50f)
        buttonSend.setPosition(152f, 152f)
        inputOtherPeerId.setPosition(0f, 100f)
        inputMessage.setPosition(0f, 150f)
        labelMessage.setPosition(0f, 200f)

        buttonStart.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
//                game.setNextScreen(Board(game))
            }
        })

        buttonConnect.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                log("buttonConnect clicked")
                game._dataConnection.setup({
                    log("connection call back")
                    labelMessage.setText(it)
                    return@setup it
                })

                log("other peer id :" + inputOtherPeerId.text)
                if (inputOtherPeerId.text != "") {
                    log("connection other peer id :" + inputOtherPeerId.text)
                    game._dataConnection.connecting(inputOtherPeerId.text)
                }
            }
        })

        buttonDisconnect.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                log("buttonDisconnect clicked")
                game._dataConnection.destroyPeer()
            }
        })

        buttonSend.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent, x: Float, y: Float) {
                log("buttonSend clicked")
                game._dataConnection.sendData(inputMessage.text)
            }
        })

        stage.addActor(buttonStart)

        stage.addActor(buttonConnect)
        stage.addActor(buttonDisconnect)
        stage.addActor(buttonSend)
        stage.addActor(inputOtherPeerId)
        stage.addActor(inputMessage)
        stage.addActor(labelMessage)
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