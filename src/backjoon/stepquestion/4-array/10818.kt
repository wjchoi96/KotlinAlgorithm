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
    var max = Int.MIN_VALUE
    var min = Int.MAX_VALUE
    val size = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    for( i in 0 until size ){
        val value = st.nextToken().toInt()
        if( value >= max )
            max = value
        if( value <= min )
            min = value
    }
    bw.write("$min $max")

    bw.flush()
    br.close()
    bw.close()
}