package ja.x28go.game.reversi.model

/**
 * Game main data
 */

class MainData() {

    // const value
    val BOARD_SIZE = 8  // board size X x X


    // default palce
    private val CENTER_LEFT_UPPER  = ja.x28go.game.reversi.model.Place(BOARD_SIZE / 2 - 1, BOARD_SIZE / 2 - 1, ja.x28go.game.reversi.model.Stone.BLACK)
    private val CENTER_LEFT_UNDER  = ja.x28go.game.reversi.model.Place(BOARD_SIZE / 2 - 1, BOARD_SIZE / 2, ja.x28go.game.reversi.model.Stone.WHITE)
    private val CENTER_RIGHT_UPPER = ja.x28go.game.reversi.model.Place(BOARD_SIZE / 2, BOARD_SIZE / 2 - 1, ja.x28go.game.reversi.model.Stone.WHITE)
    private val CENTER_RIGHT_UNDER = ja.x28go.game.reversi.model.Place(BOARD_SIZE / 2, BOARD_SIZE / 2, ja.x28go.game.reversi.model.Stone.BLACK)

}
