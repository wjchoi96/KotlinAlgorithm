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
    var count = 0
    val originX = st.nextToken().toInt()
    var x = originX
    while(true){
        count++
        val decValue = if( x < 10 ) 0 else x / 10
        val value = x % 10
        val sum = decValue + value
        // 2 + 6 = 8 -> 68 -> value * 10 + sum
        // 6 + 8 = 14 -> 84 -> value * 10 + sum % 10
        x = if( sum >= 10 ) ( value * 10 ) + ( sum % 10 ) else sum + ( value * 10 )
        println("$decValue + $value = $sum ==> result $x")
        if( x == originX )
            break
            
    }

    bw.write("count : $count")
    
    bw.flush()
    br.close()
    bw.close()
}