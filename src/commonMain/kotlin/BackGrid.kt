import com.soywiz.korge.view.*
import com.soywiz.korma.geom.Point


class BackGrid(val stage: Stage) {

    val cameraContainer = stage.container()
    val gridContainer = cameraContainer.container()
    val noteContainer = cameraContainer.container()
    val auditContainer = cameraContainer.container()

    val LINE_AMOUNT = 2
    val BAR_PROPERTION = 1/7.0

    val halfWidth get() = stage.width/2
    val halfHeight get() = stage.height/2
    val lineWidth get() = stage.width / LINE_AMOUNT
    val upperBar get() = (1 - BAR_PROPERTION) * halfHeight
    val clickBar get() = BAR_PROPERTION * halfHeight
    val auditLine get() = upperBar - clickBar*3/2

    init {

        stage.apply {
            repeat(LINE_AMOUNT + 1) {
                val x = it * lineWidth - halfWidth
                gridContainer.line(Point(x + 1, -halfHeight), Point(x + 1, halfHeight))
            }
            val barOffset = -1/7.0 * stage.height
            val gridBarAdder = 1/25.0 * stage.height
            val upperLine = upperBar + barOffset - gridBarAdder/2
            gridContainer.line(Point(-halfWidth, upperLine), Point(halfWidth, upperLine))
            val bottomLine = upperBar + clickBar + barOffset + gridBarAdder/2
            gridContainer.line(Point(-halfWidth, bottomLine), Point(halfWidth, bottomLine))
            cameraContainer.centerOnStage()
        }


    }

}