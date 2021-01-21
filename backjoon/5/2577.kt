import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(arge : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    
    val array : Array<Int> = Array(10) { 0 }

    val x = br.readLine().toInt()
    val y = br.readLine().toInt()
    val z = br.readLine().toInt()
    val result = (x * y * z).toString()
    for(i in 0 until result.count()){
        val index = result[i].toString().toInt()
        array[index]++
    }
    for(i in 0 until array.size){
        bw.write("${array[i]}\n")
    }

    bw.flush()
    br.close()
    bw.close()
}