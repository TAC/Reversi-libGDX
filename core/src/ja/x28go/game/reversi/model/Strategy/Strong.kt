package ja.x28go.game.reversi.model.Strategy

import ja.x28go.game.reversi.model.BoardData
import ja.x28go.game.reversi.model.Cpu
import ja.x28go.game.reversi.model.Place
import ja.x28go.game.reversi.model.Stone

/**
 * CPU strong
 */

class Strong : Cpu {
    val boardRatings = arrayOf(
            arrayOf(30, -12, 0, -1, -1, 0, -12, 30),
            arrayOf(-12, -15, -3, -3, -3, -3, -15, -12),
            arrayOf(0, -3, 0, -1, -1, 0, -3, -1),
            arrayOf(-1, -3, -1, -1, -1, -1, -3, -1),
            arrayOf(-1, -3, -1, -1, -1, -1, -3, -1),
            arrayOf(0, -3, 0, -1, -1, 0, -3, -1),
            arrayOf(-12, -15, -3, -3, -3, -3, -15, -12),
            arrayOf(30, -12, 0, -1, -1, 0, -12, 30)
    )

    override fun computeNext(data: BoardData, color: Stone): Place {
        return data.boardStatus.flatMap { it }
                .filter { data.canPut(Place(it.x, it.y, color)) }
                .maxBy { checkScore(it) + data.getCanChangePlaces(it).map { checkScore(it) }.sum() }!!
    }

    private fun checkScore(place: Place) = boardRatings[place.x][place.y]
}