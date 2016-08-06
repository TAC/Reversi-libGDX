package ja.x28go.game.reversi.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import ja.x28go.game.reversi.Main

/**
 * splash screen
 */

class Splash(game: Main) : ScreenAdapter(game) {

    lateinit var stage: Stage

    init {
        log("board init")

        stage = Stage(FitViewport(game.LOGICAL_WIDTH, game.LOGICAL_HEIGHT))
        Gdx.input.inputProcessor = stage

        // スプラッシュ画像
        var texture = Texture(Gdx.files.internal("image/splash.png"))
        var image = Image(texture)

        stage.addActor(image)
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