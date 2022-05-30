import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(arge : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
 
    val value = br.readLine().toString()
    for(i in 97 until 123){
        var wordIndex = -1
        for( index in value.indices ){
            if( i.toChar() == value[index]){
                println("${i.toChar()}, ${value[index]}")
                wordIndex = index
                break
            }
            bw.write("$wordIndex ")
        }
    } // a== 97 z == 122
   
    bw.flush()
    br.close()
    bw.close()
}


