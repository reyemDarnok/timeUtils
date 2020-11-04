package timeAnalysis

import java.lang.Math.pow
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sqrt

fun linearRegression(data: Map<Double,Double>) : RegressionResult{
    if(data.size < 2){
        return if(data.isEmpty()){
            RegressionResult(1.0,0.0,0.0)
        } else {
            val slope = data.entries.first().value / data.entries.first().key
            RegressionResult(1.0,slope,0.0)
        }
    }
    val xAverage = data.keys.average()
    val yAverage = data.values.average()

    val reducedByMean = data.mapKeys { it.key - xAverage }.mapValues { it.value - yAverage }

    val xStandardDeviation = sqrt(reducedByMean.keys.sumByDouble { it * it } / (data.size - 1))
    val yStandardDeviation = sqrt(reducedByMean.values.sumByDouble { it * it } / (data.size - 1))

    val covariance = reducedByMean.entries.sumByDouble {it.key * it.value} / (data.size - 1)
    val correlation = covariance / (xStandardDeviation  * yStandardDeviation)

    val slope = yStandardDeviation / xStandardDeviation * correlation
    val axisIntercept = yAverage - slope * xAverage

    return RegressionResult(correlation,slope,axisIntercept)
}

fun powerTest(data: Map<Double, Double>): PowerTestResult{
    val logMap = data.mapKeys { log10(it.key) }.mapValues { log10(it.value) }
    val regression = linearRegression(logMap)
    return PowerTestResult(10.0.pow(regression.axisIntercept),regression.slope, regression.correlation)
}

fun polynomialRuntimeTest(minimum: Int = 1): PowerTestResult{

    return PowerTestResult(0.0,0.0,0.0)
}