// bronze 5

import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader

fun main(args: Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`)) // ~
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var st : StringTokenizer

    st = StringTokenizer(br.readLine())
    
    bw.write("${st.nextToken().toInt() - 543}")
    //2541 - 443 = 1998
    

    bw.flush()
    bw.close()
    br.close()
}
