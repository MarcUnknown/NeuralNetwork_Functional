import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.sqrt

enum class LossFunctions{
    MSE,
    RMSE,
    MAE,
    Categorical
}

class LossFunctionMath{
    private val meanSquaredError : (targets : Matrix, predictions : Matrix) -> Double = { targets, predictions ->
        1.0 / (targets.getColumns() * targets.getRows()) * predictions.getElements().flatMapIndexed { rowIndex, row ->
            row.mapIndexed { columnIndex, element ->
                (element - targets.getElements()[rowIndex][columnIndex]).pow(2.0)
            }
        }.sum()
    }
    private val rootMeanSquaredError : (targets : Matrix, predictions : Matrix) -> Double = { targets, predictions ->
        sqrt(meanSquaredError(targets, predictions))
    }
    private val meanAbsoluteError : (targets : Matrix, predictions : Matrix) -> Double = { targets, predictions ->
        1.0 / (targets.getColumns() * targets.getRows()) * predictions.getElements().flatMapIndexed { rowIndex, row ->
            row.mapIndexed { columnIndex, element ->
                abs(element - targets.getElements()[rowIndex][columnIndex])
            }
        }.sum()
    }
    private val categorical : (targets : Matrix, predictions : Matrix) -> Double = { targets, predictions ->
        -predictions.getElements().flatMapIndexed { rowIndex, row ->
            row.mapIndexed { columnIndex, element ->
                ln(element) * targets.getElements()[rowIndex][columnIndex]
            }
        }.sum()
    }

    fun getLossFunction (lossFunction: LossFunctions): (Matrix, Matrix) -> Double {
        require(true) { IllegalArgumentException("Loss function needs to be set!") }
        return when(lossFunction){
            LossFunctions.MSE -> meanSquaredError
            LossFunctions.RMSE -> rootMeanSquaredError
            LossFunctions.MAE -> meanAbsoluteError
            LossFunctions.Categorical -> categorical
        }
    }
}