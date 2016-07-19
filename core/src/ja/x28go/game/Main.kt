package ja.x28go.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import ja.x28go.game.screen.Menu

class Main : Game(), Log {

    // 4:3
    var LOGICAL_WIDTH = 256f
    var LOGICAL_HEIGHT = 192f

    private var nextScreen: Screen? = null

    override fun create() {
        this.log("create")

        setScreen(Menu(this))
    }

    override fun render() {
        super.render()
        if (nextScreen != null) {
            this.log("render setScreen")
            super.setScreen(nextScreen)
            nextScreen = null
        }
    }

    override fun resize(width: Int, height: Int) {
        this.log("resize($width,$height)")
    }

    override fun dispose() {
        this.log("dispose")
        super.dispose()
    }

    override fun setScreen(screen: Screen) {
        this.log("setScreen")
        nextScreen = screen
    }
}
