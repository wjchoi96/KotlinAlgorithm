import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(arge : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    
    var value1 = st.nextToken().toString().reversed().toInt()
    var value2 = st.nextToken().toString().reversed().toInt()
    if( value1 > value2 )
        bw.write("$value1")
    else
        bw.write("$value2")
   
    bw.flush()
    br.close()
    bw.close()
}


