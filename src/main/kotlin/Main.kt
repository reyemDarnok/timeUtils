import timeAnalysis.linearRegression

object Main {
    @JvmStatic
    fun main(vararg test: String){
        val testValues = HashMap<Double,Double>()
        testValues[1.0] = 1.0
        testValues[2.0] = 2.0
        //testValues[3.0] = 3.0
        //testValues[4.0] = 4.0
        println(linearRegression(testValues))
    }
}