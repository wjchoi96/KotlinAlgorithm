import java.util.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.InputStreamReader
fun main(arge : Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
 
    val value = br.readLine().toString()
    var sum = 0
    for(i in value) {
        sum += getTimeToPressNumber(getPhoneNumFromWord(i))
    }

    bw.write("$sum")
   
    bw.flush()
    br.close()
    bw.close()
}

private fun getPhoneNumFromWord(word : Char) : Int {
    return when(word) {
        'A','B','C' -> 2
        'D','E','F' -> 3
        'G','H','I' -> 4
        'J','K','L' -> 5
        'M','N','O' -> 6
        'P','Q','R','S' -> 7
        'T','U','V' -> 8
        'W','X','Y','Z' -> 9
        else -> 0
    }
}
private fun getTimeToPressNumber(number : Int) : Int {
    // 숫자를 하나 누르면 다이얼이 처음 위치로 돌아간다 -> 한번 누르면 처음부터 시작
    // 1을 걸려면 1초
    // 1보다 더 큰수를 걸리는데 시간은 이보다 더 걸림
    // 한칸옆에 있는 숫자를 걸기 위해선 1초씩 더 걸린다
    // 처음위치 -1 ( 1까지 가는데 2초가 걸리기때문 -> 1칸 가는데 1초 )
    // UNUCIC -> 868242 -> 10, 8, 10, 4, 6, 4 -> 42 ?? 정답은 36 ( 6이 더 나온걸로 봐선 1초씩 더 계산한거같다 )
    // 868242 -> 9, 7, 9, 3, 5, 3 -> 36
    return ( number + 1 )
}


