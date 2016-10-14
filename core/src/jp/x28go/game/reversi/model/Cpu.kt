package jp.x28go.game.reversi.model

import jp.x28go.game.reversi.model.Stone
import java.io.Serializable

/**
 * CPU Class interface
 */

interface Cpu : Serializable {
    fun computeNext(data: BoardData, color: Stone): Place
}