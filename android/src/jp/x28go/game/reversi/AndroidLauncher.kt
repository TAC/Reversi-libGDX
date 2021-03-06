package jp.x28go.game.reversi

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()

        // SkyWay
        val dataConnection = SkyWayDataConnection(context)

        initialize(Main(dataConnection), config)
    }
}
