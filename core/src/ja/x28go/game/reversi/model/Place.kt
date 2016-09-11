package ja.x28go.game.reversi.model

import com.badlogic.gdx.scenes.scene2d.ui.Image

/**
 * put place axis management
 */

data class Place(val x: Int, val y: Int, var stone: Stone, var image: Image? = null) {

    override fun equals(other: Any?): Boolean {
        val check = other as? Place ?: return false
        return x == check.x && y == check.y && stone == check.stone
    }

    override fun hashCode(): Int {
        var result = x
        result += 31 * result + y
        result += 31 * result * stone.hashCode()
        return result
    }
}
