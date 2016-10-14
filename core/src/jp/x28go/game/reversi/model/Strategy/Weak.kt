package jp.x28go.game.reversi.model.Strategy

import jp.x28go.game.reversi.model.BoardData
import jp.x28go.game.reversi.model.Cpu
import jp.x28go.game.reversi.model.Place
import jp.x28go.game.reversi.model.Stone

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