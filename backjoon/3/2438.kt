import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(arge : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st: StringTokenizer
    st = StringTokenizer(br.readLine())
    for(i in 1 until (st.nextToken().toInt() + 1) ){
        for( i in 0 until i ){
            bw.write("*")
        }
        bw.write("\n")
    }
    bw.flush()
    br.close()
    bw.close()
}