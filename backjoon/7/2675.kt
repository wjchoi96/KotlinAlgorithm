import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(arge : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st : StringTokenizer
 
    val size = br.readLine().toInt()
    for( loop in 0 until size ) {
        st = StringTokenizer(br.readLine())
        val count = st.nextToken().toInt()
        val word = st.nextToken().toString()
        for( j in word ){
            for( i in 0 until count ){
                bw.write("$j")
            }
        }
        bw.write("\n")
    }
   
    bw.flush()
    br.close()
    bw.close()
}


