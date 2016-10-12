package ja.x28go.game.reversi.model

import java.io.Serializable

/**
 * CPU Class interface
 */

interface Cpu : Serializable {
    fun computeNext(data: BoardData, color: Stone): Place
}