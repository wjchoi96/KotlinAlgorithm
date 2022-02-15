// silver 5
// 9-2

import java.io.*
import kotlin.io.readLine

fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val min = br.readLine().toInt()
    val max = br.readLine().toInt()

    var sum = 0
    var minSosu : Int = -1
    for(i in min until max + 1){
        if(isSosu(i)) {
            if(sum == 0)
                minSosu = i
            sum+=i
        }
    }

    if(sum == 0)
        bw.write("-1\n")
    else {
        bw.write("$sum\n$minSosu\n")
    }

    bw.flush()
    bw.close()
    br.close()
}

private fun getSosuSum(min : Int, max : Int, bw : BufferedWriter) : Int {
    var sum = 0
    for(i in min until max + 1){
        if(isSosu(i)) {
            if(sum == 0)
                bw.write("$i\n")
            sum+=i
        }
    }
    return sum
}

private fun isSosu(value : Int) : Boolean {
    if(value == 1 || value == 0) return false
    for(i in 2 until value)
        if(value % i == 0) return false
    return true
}