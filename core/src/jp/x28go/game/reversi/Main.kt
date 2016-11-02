package jp.x28go.game.reversi

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import jp.x28go.game.reversi.screen.Menu

class Main(dataConnection: SkyWayDataConnectionInterface) : Game(), Log {

    // 3:4
    var LOGICAL_WIDTH = 192f
    var LOGICAL_HEIGHT = 256f

    private var nextScreen: Screen? = null

    var _dataConnection: SkyWayDataConnectionInterface

    init {
        _dataConnection = dataConnection
    }

    override fun create() {
        this.log("create")

//        setScreen(Splash(this))
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
        _dataConnection.destroyPeer()
    }

    fun setNextScreen(screen: Screen) {
        this.log("setNextScreen")
        nextScreen = screen
    }
}
