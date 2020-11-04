import timeAnalysis.generators.list
import timeAnalysis.linearRegression
import timeAnalysis.polynomialRuntimeTest
import timeAnalysis.powerTest

object Main {
    @JvmStatic
    fun main(vararg test: String){
        println(polynomialRuntimeTest(maximum = 1000,iterations = 10000,generator = ::list,function = ::sort))
    }
}

fun sort(data: MutableList<Int>){
    for(i in 0 until data.size - 1){
        for(inner in i until data.size - 1){
            if(data[inner] > data[inner + 1]){
                val tmp = data[inner]
                data[inner] = data[inner + 1]
                data[inner + 1] = tmp
            }
        }
    }
}