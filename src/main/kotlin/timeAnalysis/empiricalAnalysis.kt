package timeAnalysis

import java.lang.Math.pow
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random
import kotlin.system.measureNanoTime

fun linearRegression(data: Map<Double,Double>) : RegressionResult{
    val nReduced = data.size - 1
    if(nReduced < 1){
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

    val xStandardDeviation = sqrt(reducedByMean.keys.sumByDouble { it * it } / nReduced)
    val yStandardDeviation = sqrt(reducedByMean.values.sumByDouble { it * it } / nReduced)

    val covariance = reducedByMean.entries.sumByDouble {it.key * it.value} / nReduced
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

fun <T> polynomialRuntimeTest(minimum: Int = 1,maximum: Int = 1e6.toInt(),iterations: Int = 1000, generator: (Int) -> T,function: (T) -> Any?): PowerTestResult{
    val times = HashMap<Double,Double>(iterations)
    for(i in 0 until iterations){
        val randInt = Random.nextInt(minimum,maximum)
        val problem = generator(randInt)
        val runtime = measureNanoTime { function(problem) }
        times[randInt.toDouble()] = runtime.toDouble()
    }
    return powerTest(times)
}