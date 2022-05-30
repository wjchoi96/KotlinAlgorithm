// silver 5
// 16-2
/*
    A가 N의 약수가 되려면 
    1. N이 A의 배수
    2. A가 1과 N이 아니여야한다

    어떤 수 N의 진짜 모든 약수가 주어질때, N을 구하시오

    1 <= 개수 <= 50

    => 목록중에 min * max 를 하면 N이 나온다
*/
import java.io.*
import java.util.StringTokenizer 

fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    
    val size = br.readLine().toInt()
    val arr = Array<Int>(size){-1}
    val st = StringTokenizer(br.readLine())

    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE
    for(i in 0 until size){
        arr[i] = st.nextToken().toInt()
        min = Math.min(min, arr[i])
        max = Math.max(max, arr[i])
    }

    bw.write("${max*min}\n")
  
    bw.flush()
    bw.close()
    br.close()
}
