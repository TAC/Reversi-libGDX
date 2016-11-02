package jp.x28go.game.reversi

/**
 * SkyWay Data Connection Interface
 */

interface SkyWayDataConnectionInterface {
    fun setup(dataCallback: (data: String) -> String)
    fun connecting(peerId: String)
    fun listingPeers()
    fun destroyPeer()
    fun sendData(data: String)
}