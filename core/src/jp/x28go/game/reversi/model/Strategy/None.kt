package jp.x28go.game.reversi.model.Strategy

import jp.x28go.game.reversi.model.BoardData
import jp.x28go.game.reversi.model.Cpu
import jp.x28go.game.reversi.model.Place
import jp.x28go.game.reversi.model.Stone

/**
 * CPU none
 */

class None : Cpu {
    override fun computeNext(data: BoardData, color: Stone): Place {
        throw IllegalAccessException()
    }
}