import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(arge : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    
    val value = br.readLine().toString().split(" ")
    var count = 0
    for(i in value){
        if(!i.isNullOrBlank())
            count++
    }
    bw.write("$count")
   
    bw.flush()
    br.close()
    bw.close()
}