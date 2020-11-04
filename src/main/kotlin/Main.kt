import timeAnalysis.linearRegression
import timeAnalysis.powerTest

object Main {
    @JvmStatic
    fun main(vararg test: String){
        val testValues = HashMap<Double,Double>()
        testValues[1.0] = 10.0
        testValues[2.0] = 40.0
        testValues[3.0] = 90.0
        testValues[4.0] = 160.0
        println(powerTest(testValues))
    }
}