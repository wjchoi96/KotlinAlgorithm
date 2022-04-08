// 15-15
// sliver 2

/*
    접근 방향을 모르곘다면
    검색하기 이전에
    테스트 케이스들을 진행해서 규칙을 찾아봐라

    n개의 정수로 이루어진 수열

    몇개의 수를 선택해 구할 수 있는 합중 가장 큰 합

    수는 한개 이상 선택

    10, -4, 3, 1, 5, 6, -35, 12, 21, -1

    dp[n] : n번째 까지의 연속된 값의 합 or 자기자신 값 중에 큰값
    dp[n+1] : max(getValue[n] + this, this)

    이러고 각 dp 를 순회하여 가장 큰 값을 구한다

    dp[1] : 10
    dp[2] : 10
    - dp[1] + arr[2] < arr[2]
    dp[3] : 10
    => 10 -4 + 3 < 10
    - dp[1] + arr[2] + arr[3] < dp[2]
    dp[4] : 10 
    => 10 -4 + 3 + 1 == 10
    - dp[1] + arr[2] + arr[3] + arr[4] == dp[3]
    dp[5] : 15 
    => 10 -4 +3 + 1 + 5 => 15
    dp[6] : 21 
    => dp[5] + 6
    dp[7] : 21 
    => dp[6] + arr[7] < dp[6]
    dp[8] : 21 =>
    - 
    

    dp[10] : 33
    - dp[9] + arr[10] 
*/

import java.io.*
import java.util.StringTokenizer
lateinit var dpSum : Array<Int?>
lateinit var arrValue : Array<Int>
var max = Int.MIN_VALUE
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val size = br.readLine().toInt()
    dpSum = Array(size+1){null}
    arrValue = Array(size +1){-1}

    val st = StringTokenizer(br.readLine())
    for(i in 1 until size+1){
        arrValue[i] = st.nextToken().toInt()
    }
    dpSum[0] = 0
    dpSum[1] = arrValue[1]

    getSumValue(size)
    bw.write("$max\n")

    bw.flush()
    bw.close()
    br.close()
}

private fun getSumValue(n : Int) : Int{
    if(dpSum[n] == null){
        dpSum[n] = Math.max(getSumValue(n-1) + arrValue[n], arrValue[n])
    }
    max = Math.max(max, dpSum[n]!!)
    return dpSum[n]!!
}