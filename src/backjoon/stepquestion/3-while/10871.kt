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
    val size = st.nextToken().toInt()
    val max = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    for( i in 0 until size ){
        val x = st.nextToken().toInt()
        if( x < max )
            bw.write("$x ")
    }
    

    bw.flush()
    br.close()
    bw.close()
}