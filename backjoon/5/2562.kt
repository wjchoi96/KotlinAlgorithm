import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(arge : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var maxCount = 0 
    var max = Int.MIN_VALUE
    for( i in 0 until 9 ){
        val value = br.readLine().toInt()
        if (value >= max ){
            max = value
            maxCount = i + 1
        }
    }
   bw.write("$max\n$maxCount")

    bw.flush()
    br.close()
    bw.close()
}