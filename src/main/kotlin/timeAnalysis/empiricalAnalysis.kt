package timeAnalysis

import kotlin.math.sqrt

fun linearRegression(data: Map<Double,Double>) : RegressionResult{
    val xAverage = data.keys.average()
    val yAverage = data.values.average()

    val xStandardDeviation = sqrt(data.keys.sumByDouble { (it - xAverage) * (it - xAverage) } / (data.size - 1))
    val yStandardDeviation = sqrt(data.values.sumByDouble { (it - xAverage) * (it - xAverage) } / (data.size - 1))

    println(xAverage)
    println(xStandardDeviation)

    val covariance = data.entries.sumByDouble {(it.key - xAverage) * (it.value - yAverage)} / (data.size -1)

    val correlation = covariance / (xStandardDeviation  * yStandardDeviation)

    println(covariance)

    val slope = yStandardDeviation / xStandardDeviation * correlation
    val axisIntercept = yAverage - slope * xAverage

    return RegressionResult(correlation,slope,axisIntercept)
}