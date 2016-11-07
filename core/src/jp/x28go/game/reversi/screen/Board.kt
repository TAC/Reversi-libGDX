package jp.x28go.game.reversi.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.FitViewport
import jp.x28go.game.reversi.Main
import jp.x28go.game.reversi.model.BoardData
import jp.x28go.game.reversi.model.Place
import jp.x28go.game.reversi.model.Player
import jp.x28go.game.reversi.model.Stone
import jp.x28go.game.reversi.model.Strategy.Weak

/**
 * board screen
 */

class Board(game: Main) : jp.x28go.game.reversi.screen.ScreenAdapter(game) {

    val textureBoard: Texture
    val textureBlack: Texture
    val textureWhite: Texture
    val textureSelect: Texture
    val labelStatus: Label

    val BOARD_START_X = 20.0f
    val BOARD_START_Y = 204.0f
    val SQUARE_WIDTH  = 19.2f
    val SQUARE_HEIGHT = 19.1f

    val SELECT_START_X = 19.1f
    val SELECT_START_Y = 203.4f
    val SELECT_WIDTH   = 19.2f
    val SELECT_HEIGHT  = 19.2f

    private val data: BoardData = BoardData()

    var currentPlayer = Stone.BLACK

    var playerOwn: Player
    var playerOther: Player

    init {
        log("board init")

        stage = Stage(FitViewport(game.LOGICAL_WIDTH, game.LOGICAL_HEIGHT))
        Gdx.input.inputProcessor = stage

        // 画像読み込み
        textureBoard = Texture(Gdx.files.internal("image/board.png")) // 盤面画像
        textureBlack = Texture(Gdx.files.internal("image/black.png")) // 黒コマ画像
        textureWhite = Texture(Gdx.files.internal("image/white.png")) // 白コマ画像
        textureSelect = Texture(Gdx.files.internal("image/select.png")) // 選択可能マス画像

        // 初期表示
        var imageBoard = Image(textureBoard)
        imageBoard.setScale(0.4f, 0.4f)
        imageBoard.setPosition(0f, 50f)
        stage.addActor(imageBoard)

        // プレイヤーの設定
//        playerOwn = Player(1, "You", 0, 0, currentPlayer.random())
        playerOwn = Player(1, "You", 0, 0, currentPlayer)
        playerOther = Player(2, "Enemy", 0, 0, playerOwn.stone.other(), Weak())

        // 石の初期表示
        data.getInitialPlaces().forEach { putStone(it) }

        // 配置可能場所の表示
        data.getAllCanPutPlaces(currentPlayer).forEach { setSelect(it) }

        // ラベル初期化
        labelStatus = Label("TURN IS BLACK", skin, "default")
        labelStatus.setPosition(0f, 0f)
        stage.addActor(labelStatus)
    }

    override fun update(delta: Float) {
    }

    override fun draw(delta: Float) {
        stage.draw()
    }

    fun putStone(place: Place) {
        val image = when (place.stone) {
            Stone.BLACK -> Image(textureBlack)
            Stone.WHITE -> Image(textureWhite)
            else -> throw IllegalArgumentException()
        }

        // 置かれている石を置き換える
        var nowStone = data.boardStatus[place.x][place.y].stone
        if (nowStone != Stone.NONE) {
            data.boardStatus[place.x][place.y].image?.remove()
        }

        // 石を配置
        image.setScale(0.4f, 0.4f)
        setStonePosition(image, place.x, place.y)
        stage.addActor(image)

        // 配置を記録
        data.boardStatus[place.x][place.y].stone = place.stone
        data.boardStatus[place.x][place.y].image = image
    }

    fun setSelect(place: Place) {
        if (place.stone != Stone.NONE) {
            return
        }

        // 配置可能場所の設定
        var image = Image(textureSelect)
        image.setScale(0.4f, 0.4f)
        setSelectPosition(image, place.x, place.y)

        if (playerOwn.stone == currentPlayer) {
            // クリックイベントの設定
            image.addListener(object : ClickListener() {
                override fun clicked(event: InputEvent, x: Float, y: Float) {
                    onClickPlace(place)
                }
            })
        }

        stage.addActor(image)

        // 配置を記録
        data.boardStatus[place.x][place.y].stone = Stone.SELECT
        data.boardStatus[place.x][place.y].image = image
    }

    fun setStonePosition(stone: Image, x: Int, y: Int) {
        var posX = BOARD_START_X + (x * SQUARE_WIDTH)
        var posY = BOARD_START_Y - (y * SQUARE_HEIGHT)
        stone.setPosition(posX, posY)
    }

    fun setSelectPosition(select: Image, x: Int, y: Int) {
        var posX = SELECT_START_X + (x * SELECT_WIDTH)
        var posY = SELECT_START_Y - (y * SELECT_HEIGHT)
        select.setPosition(posX, posY)
    }

    fun onClickPlace(place: Place) {
        log("onClickPlace - x:" + place.x.toString() + " y:" + place.y.toString())
        val clickPlace = Place(place.x, place.y, currentPlayer)
        if (!data.canPut(clickPlace)) {
            log("onClickPlace - canPut:false")
            return
        }

        // 配置可能場所をリセット
        clearAllSelectPlaces()

        // 石を配置
        putStone(clickPlace)

        // 裏返し処理
        data.getCanChangePlaces(clickPlace).forEach { putStone(it) }

        // 終了処理
        if (data.isGameOver()) {
            val blackCount = data.countStones(Stone.BLACK)
            val whiteCount = data.countStones(Stone.WHITE)
            if (blackCount > whiteCount) {
                labelStatus.setText("BLACK WIN")
            } else if (blackCount < whiteCount) {
                labelStatus.setText("WHITE WIN")
            } else {
                labelStatus.setText("DRAW")
            }
            return
        }

        // ターン切り替え
        changePlayer()

        // 配置可能場所の表示
        data.getAllCanPutPlaces(currentPlayer).forEach { setSelect(it) }

        // パス
        if (!data.canNext(currentPlayer)) {
            clearAllSelectPlaces()
            changePlayer()
            data.getAllCanPutPlaces(currentPlayer).forEach { setSelect(it) }
        }

        // 相手の処理
        if (playerOther.stone == currentPlayer) {
            onClickPlace(playerOther.strategy.computeNext(data, playerOther.stone))
        }
    }

    fun clearAllSelectPlaces() {
        data.getAllSelectPlaces().forEach {
            it.image?.remove()
            it.stone = Stone.NONE
        }
    }

    fun changePlayer() {
        currentPlayer = currentPlayer.other()
        labelStatus.setText(when (currentPlayer) {
            Stone.BLACK -> "TURN IS BLACK"
            Stone.WHITE -> "TURN IS WHITE"
            else -> throw IllegalArgumentException()
        })
    }
}