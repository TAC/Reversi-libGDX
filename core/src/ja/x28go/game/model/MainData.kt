package ja.x28go.game.model

/**
 * Game main data
 */

class MainData() {

    // const value
    val BOARD_SIZE = 8  // board size X x X


    // default palce
    private val CENTER_LEFT_UPPER  = Place(BOARD_SIZE / 2 - 1, BOARD_SIZE / 2 - 1, Stone.BLACK)
    private val CENTER_LEFT_UNDER  = Place(BOARD_SIZE / 2 - 1, BOARD_SIZE / 2,     Stone.WHITE)
    private val CENTER_RIGHT_UPPER = Place(BOARD_SIZE / 2,     BOARD_SIZE / 2 - 1, Stone.WHITE)
    private val CENTER_RIGHT_UNDER = Place(BOARD_SIZE / 2,     BOARD_SIZE / 2,     Stone.BLACK)

}
