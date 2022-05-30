import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st: StringTokenizer

    st = StringTokenizer(br.readLine())
    val size = st.nextToken().toInt()
    for(i in 0 until size){
        st = StringTokenizer(br.readLine())
        //bw.write("${st.nextToken().toInt() + st.nextToken().toInt()}\n")
        println("${st.nextToken().toInt() + st.nextToken().toInt()}")
    }
    //bw.flush()
    br.close()
    bw.close()

}