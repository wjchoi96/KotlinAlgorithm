import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(arge : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st: StringTokenizer
  
    while(true) {
        try {
            st = StringTokenizer(br.readLine())
        }catch(e : Exception){
            break
        }
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()
        bw.write("${x + y}\n")
    }

    bw.flush()
    br.close()
    bw.close()
}