package timeAnalysis.generators

import kotlin.random.Random

fun list(num: Int): MutableList<Int>{
    return MutableList(num) { Random.nextInt() }
}