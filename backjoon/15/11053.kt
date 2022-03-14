//15-11
//sliver 2

/*
    수열 a 가 주어졌을때 가잔 긴 증가하는 부분 수열?
    A = {10, 20, 10, 30, 20, 50}
    => {10, 20, 30, 50} 길이는 4

    증가하는 수열이기만 하면 되는거구만
    => 가장 긴 길이

    1<= n <= 1000
    1<= 수열을 이루는 수 <= 1000

    n 이 2일때
    n-1 과 자기자신을 비교, 더 작은쪽

    n 이 3일때
    
    아 이거도 애매하네
    자기 자신이 포함 안될수도 있네
*/

import java.io.*
import java.util.StringTokenizer
lateinit var numArr : Array<Int>
lateinit var numDpArr : Array<Int>
fun main(arg : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val size = br.readLine().toInt()

    numArr = Array(size+1){0}
    numDpArr = Array(size+1){-1}

    val st = StringTokenizer(br.readLine())
    for(i in 1 until size + 1){
        numArr[i] = st.nextToken().toInt()
    }


    br.close()
    bw.flush()
    bw.close()
}

private fun initNumDpArr(){
    numDpArr[0] = numArr[0]
    numDpArr[1] = numArr[1]
}