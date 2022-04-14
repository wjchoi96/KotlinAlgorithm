import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(arge : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
 
    val size = br.readLine().toInt()
    
    val sum = if( size > 18 ){
        sum(br.readLine().toInt())
    }else{
        sumUInt(br.readLine().toUInt())
    }
    bw.write("$sum")
   
    bw.flush()
    br.close()
    bw.close()
}

fun sum(value : Int) : Int{
    var sum = 0
    var x = value
    while(true) {
        if( x == 0 )
            break
        sum += x % 10
        x = x / 10
    }
    return sum
}

fun sumUInt(value : UInt) : Int{
    var sum : UInt = 0.toUInt()
    var x = value
    while(true) {
        if( x.equals(0) )
            break
        sum = sum.toUInt() + x.rem(10.toUInt())
        x = x.div(10.toUInt())
    }
    return sum.toInt()
}

