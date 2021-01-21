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
 
    for(i in 0 until size ) {
        st = StringTokenizer(br.readLine())
        var sum = 0
        val count = st.nextToken().toInt()
        val array = Array(count) {0}
        for( index in 0 until count ){
            array[index] = st.nextToken().toInt()
            sum += array[index]
        }
        val avg = sum / count
        var upCount = 0
        for( index in 0 until count ){
            if( array[index] > avg ){
                upCount++
            }
        }
        val result = upCount.toFloat()/count.toFloat() * 100
        val roundResult = Math.round(result * 1000.0f).toFloat()
        val resultStr = String.format("%.3f", roundResult/1000)
        bw.write("$resultStr%\n")
    } 
   
    bw.flush()
    br.close()
    bw.close()
}