import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(arge : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
 
    val size = br.readLine().toInt()
    var count = 0
    //hansu(size)
    
    for( i in 0 until size ){
        if( hansu(i+1) )
            count++
    }
    bw.write("$count")
    
   
    bw.flush()
    br.close()
    bw.close()
}

// 한수라면 true 리턴
// 적어도 3자리수부터 검사 시작 <= 100
// 1의자리 - 십의자리 == 10자리 - 100자리
// 다음 loop 검사를 
private fun hansu(value : Int) : Boolean {
    if( value / 10 == 0 || value / 100 == 0 )
        return true // 1, 2자리수는 모두 한수.
    
    var x = value
    var prevMinusValue : Int = Int.MIN_VALUE
    var result : Boolean = false

    while(true) {
        if( x / 10 == 0 )
            break
        val one = x % 10
        val ten = ( x / 10 ) % 10 // 124 / 10 % 10 -> 12 % 10 -> 2
        //println("one : $one, ten : $ten")
        val minValue = one - ten
        //println("prevMinusValue : $prevMinusValue, minValue : $minValue")
        if( prevMinusValue != Int.MIN_VALUE ){ // 첫번째 요청이 아니라면
            result = (prevMinusValue == minValue) // 이전 차이와 지금 차이가 같다면 true, 다르면 false
            //println("prevMinusValue : $prevMinusValue, minValue : $minValue => result : $result")
            if( !result ) // 한번이라도 다르면 한수가 아닌것
                return result
        }
        prevMinusValue = minValue // 현재 값을 이전 값으로 전달
        x = x / 10
    }
    return result
}