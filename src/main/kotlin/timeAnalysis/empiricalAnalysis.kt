package timeAnalysis

import kotlin.math.sqrt

fun <T> linearRegression(data: Map<Double,Double>) : RegressionResult{
    val xAverage = data.keys.average()
    val yAverage = data.values.average()

    var xSumOfDeviationSquares = 0.0
    var ySumOfDeviationSquares = 0.0

    var covarianceSum = 0.0

    for(entry in data){
        val xDifference = entry.key - xAverage
        val yDifference = entry.value - yAverage
        covarianceSum += yDifference * xDifference
        xSumOfDeviationSquares += xDifference * xDifference
        ySumOfDeviationSquares += yDifference * yDifference
    }

    val xStandardDeviation = sqrt(xSumOfDeviationSquares / data.size)
    val yStandardDeviation = sqrt(ySumOfDeviationSquares / data.size)

    val covariance = covarianceSum / (data.size - 1)

    return RegressionResult(0.0,0.0,0.0,0.0)
}