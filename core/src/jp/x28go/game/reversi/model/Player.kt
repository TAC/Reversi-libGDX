package jp.x28go.game.reversi.model

import jp.x28go.game.reversi.model.Strategy.None

/**
 * Player Data
 */

data class Player(val id: Int, val name: String, val win: Int, val lose: Int, val stone: Stone, val strategy: Cpu = None()) {

    override fun equals(other: Any?): Boolean {
        val check = other as? Player ?: return false
        return id == check.id
    }

}
