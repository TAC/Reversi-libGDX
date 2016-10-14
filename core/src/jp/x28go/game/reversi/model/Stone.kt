package jp.x28go.game.reversi.model

import java.util.*

/**
 * status of stone
 */

enum class Stone {

    BLACK, WHITE, SELECT, NONE;

    fun other() = if (this == BLACK) WHITE else BLACK

    fun random() : Stone {
        return when(Math.abs(Random().nextInt()) % 2) {
            0 -> BLACK
            else -> WHITE
        }
    }
}