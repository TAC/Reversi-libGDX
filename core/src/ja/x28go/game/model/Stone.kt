package ja.x28go.game.model

/**
 * status of stone
 */

enum class Stone {

    BLACK, WHITE, NONE;

    fun other() = if (this == BLACK) WHITE else BLACK
}