import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(args : Array<String>) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val size = 10001
    val arr = Array<Boolean>(size){ false }
    for(i in 0 until size) {
        val index = d(i)
        /* 
        if( index >= size) // 이게 9993 을 초과해서 9995, 9997, 9999 를 출력하는건, 어느 시점 이후로 break 가 되어서 셀프넘버가 아닌수인 저 3개를 거두지 못한것 아닐까 싶다
            break
        arr[index] = true */


        if( index < size)
            arr[index] = true
    }
    for(i in 0 until size){
        if( !arr[i] )
            bw.write("$i\n")
    }
  
    bw.flush()
    bw.close()
}

private fun d(value : Int) : Int {
    var x = value
    var sum = value
    while(true){
        if( x == 0 ) 
            break
        sum += x%10
        x = x/10
    }
    return sum
}