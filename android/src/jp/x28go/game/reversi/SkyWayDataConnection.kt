package jp.x28go.game.reversi

import android.content.Context
import io.skyway.Peer.*

/**
 * SkyWay Data Connection
 */

class SkyWayDataConnection(context: Context) : SkyWayDataConnectionInterface, Log {

    var _peer: Peer? = null
    var _data: DataConnection? = null
    var _id: String = ""
    var _connecting: Boolean = false
    var _context: Context
    lateinit var _dataCallback: (data: String) -> String

    init {
        _context = context
    }

    override fun setup(dataCallback: (data: String) -> String) {
        val options = PeerOption()
        options.key = BuildConfig.SKYWAY_KEY
        options.domain = BuildConfig.SKYWAY_DOMAIN
        options.debug = Peer.DebugLevelEnum.ALL_LOGS

        _dataCallback = dataCallback

        if (_peer == null) {
            _peer = Peer(_context, options)

            if (_peer != null) {
                setPeerCallback(_peer!!)
            }
        }
    }

    override fun connecting(peerId: String) {
        if (_peer == null) {
            return
        }

        if (_data != null) {
            _data!!.close()
            _data = null
        }

        // connect option
        val option = ConnectOption()
        option.metadata = "data connection"
        option.label = "chat"
//        option.serialization = DataConnection.SERIALIZATION_BINARY

        log("connecting")

        // connect
        _data = _peer!!.connect(peerId, option)

        if (_data != null) {
            setDataCallback(_data!!)
        }
    }

    override fun listingPeers() {

    }

    override fun destroyPeer() {
        if (_data != null) {
            unsetDataCallback(_data!!)
            _data = null
        }

        if (_peer != null) {
            unsetPeerCallback(_peer!!)
            if (!_peer!!.isDisconnected) _peer!!.disconnect()
            if (!_peer!!.isDestroyed) _peer!!.destroy()
            _peer = null
        }
    }

    override fun sendData(data: String) {
        if (_data == null) {
            log("data connection is null")
            return
        }

        val result = _data!!.send(data)
        if (true == result) {
        }
    }

    private fun setPeerCallback(peer: Peer) {
        // !!!: Event/Open
        peer.on(Peer.PeerEventEnum.OPEN, object : OnCallback {
            override fun onCallback(`object`: Any) {
                log("OPEN")
                if (`object` is String) {
                    _id = `object`
                    _dataCallback(_id)
                }
            }
        })

        // !!!: Event/Connection
        peer.on(Peer.PeerEventEnum.CONNECTION, object : OnCallback {
            override fun onCallback(`object`: Any) {
                log("CONNECTION")
                if (`object` !is DataConnection) {
                    return
                }
                _data = `object`
                setDataCallback(_data!!)
                connected()
            }
        })


        // !!!: Event/Close
        peer.on(Peer.PeerEventEnum.CLOSE, object : OnCallback {
            override fun onCallback(`object`: Any) {
                log("CLOSE")
            }
        })

        // !!!: Event/Disconnected
        peer.on(Peer.PeerEventEnum.DISCONNECTED, object : OnCallback {
            override fun onCallback(`object`: Any) {
                log("DISCONNECTED")
            }
        })

        // !!!: Event/Error
        peer.on(Peer.PeerEventEnum.ERROR, object : OnCallback {
            override fun onCallback(`object`: Any) {
                log("ERROR")
            }
        })
    }

    fun setDataCallback(data: DataConnection) {
        // !!!: DataEvent/Open
        data.on(DataConnection.DataEventEnum.OPEN, object : OnCallback {
            override fun onCallback(`object`: Any) {
                log("DATA - OPEN")
                connected()
            }
        })

        // !!!: DataEvent/Data
        data.on(DataConnection.DataEventEnum.DATA, object : OnCallback {
            override fun onCallback(`object`: Any) {
                log("DATA - DATA")
                var strValue: String = ""

                if (`object` is String) {
                    strValue = `object`
                }

                _dataCallback(strValue)
            }
        })

        // !!!: DataEvent/Close
        data.on(DataConnection.DataEventEnum.CLOSE, object : OnCallback {
            override fun onCallback(`object`: Any) {
                log("DATA - CLOSE")
                _data = null
                disconnected()
            }
        })

        // !!!: DataEvent/Error
        data.on(DataConnection.DataEventEnum.ERROR, object : OnCallback {
            override fun onCallback(`object`: Any) {
                val error = `object` as PeerError
                log("DATA - ERROR")
            }
        })
    }

    // Unset peer callback
    fun unsetPeerCallback(peer: Peer) {
        peer.on(Peer.PeerEventEnum.OPEN, null)
        peer.on(Peer.PeerEventEnum.CONNECTION, null)
        peer.on(Peer.PeerEventEnum.CALL, null)
        peer.on(Peer.PeerEventEnum.CLOSE, null)
        peer.on(Peer.PeerEventEnum.DISCONNECTED, null)
        peer.on(Peer.PeerEventEnum.ERROR, null)
    }

    // Unset data callback
    fun unsetDataCallback(data: DataConnection) {
        data.on(DataConnection.DataEventEnum.OPEN, null)
        data.on(DataConnection.DataEventEnum.DATA, null)
        data.on(DataConnection.DataEventEnum.CLOSE, null)
        data.on(DataConnection.DataEventEnum.ERROR, null)
    }

    fun connected() {
        _connecting = true
    }

    fun disconnected() {
        _connecting = false
    }

    fun closing() {
        if (false == _connecting) {
            return
        }

        disconnected()

        if (_data != null) {
            _data!!.close()
        }
    }
}
