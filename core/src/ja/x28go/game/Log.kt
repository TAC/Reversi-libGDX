package ja.x28go.game

import com.badlogic.gdx.Gdx

/**
 * output log class
 */
interface Log {
    fun log(message: String) {
        Gdx.app.log(this.javaClass.simpleName, message)
    }
}