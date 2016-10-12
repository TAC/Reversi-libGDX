package ja.x28go.game.reversi.model.Strategy

import ja.x28go.game.reversi.model.BoardData
import ja.x28go.game.reversi.model.Cpu
import ja.x28go.game.reversi.model.Place
import ja.x28go.game.reversi.model.Stone

/**
 * CPU none
 */

class None : Cpu {
    override fun computeNext(data: BoardData, color: Stone): Place {
        throw IllegalAccessException()
    }
}