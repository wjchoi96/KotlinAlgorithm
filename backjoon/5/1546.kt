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
    st = StringTokenizer(br.readLine())
    val array : Array<Int> = Array(size) { 0 }
    var max = Int.MIN_VALUE
    for( i in 0 until size ){
        array[i] = st.nextToken().toInt()
        if( array[i] >= max )
            max = array[i]
    }
    bw.write("max : $max\n")
    var sum : Float = 0.0f
    for( i in 0 until size ){
        if( array[i] != max ){
            bw.write("${array[i]} / $max * 100 = ${array[i] / max * 100}\n")
            val result = array[i].toFloat() / max.toFloat() * 100
            bw.write("$i index -> $result\n")
            sum += result
        }else{
            sum += array[i]
        }
    }
    bw.write("$sum\n")
    bw.write("${sum/size}")
   
    bw.flush()
    br.close()
    bw.close()
}