package jp.x28go.game.reversi

import com.badlogic.gdx.Gdx

/**
 * output log class
 */
interface Log {
    fun log(message: String) {
        Gdx.app.log(this.javaClass.simpleName, message)
    }
}