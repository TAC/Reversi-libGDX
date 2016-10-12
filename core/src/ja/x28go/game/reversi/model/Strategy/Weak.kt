package ja.x28go.game.reversi.model.Strategy

import ja.x28go.game.reversi.model.BoardData
import ja.x28go.game.reversi.model.Cpu
import ja.x28go.game.reversi.model.Place
import ja.x28go.game.reversi.model.Stone

/**
 * CPU weak
 */

class Weak : Cpu {
    override fun computeNext(data: BoardData, color: Stone): Place {
        return data.boardStatus.flatMap { it }
                .filter { data.canPut(Place(it.x, it.y, color)) }
                .maxBy { data.getCanChangePlaces(it).count() }!!
    }
}