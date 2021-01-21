import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(arge : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    
    val array : Array<Int> = Array(10) { 0 }
    for( i in array.indices ) {
        val value = br.readLine().toInt()
        array[i] = value % 42
    }
    var count = 0
    for( index in array.indices ){
        count++
        for( i in array.indices ){
            if( index != i && array[index] == array[i] ){
                count--
                break
            }
        }
    }
    bw.write("$count")
   
    bw.flush()
    br.close()
    bw.close()
}