package src.backjoon.stepquestion.`14-dp1`
// gold 5
// 15-16

/*
    n개의 물건 
    1 <= n <= 100

    각 물건은 w의 무게, v의 가치를 지닌다
    1<= w <= 100000
    0<= v <= 1000

    k만큼의 무게만 담을수 있는 베낭
    1<= k <= 100000

    가치의 최대값

    최대값은 long 으로 해야하려나
    1000 * 100 = 100000 => int 로 가능

    w 배열, v 배열, dp 배열 => 수용할수 있는 무게에 따른 가치
    4 7
    6 13
    4 8
    3 6
    5 12

    개수 : 4
    최대무게 : 7

    6 13
    4 8
    3 6
    5 12

    dp[0] => 수용할수있는 무게가 0
   dp  
  i   0 1 2 3 4 5  6  7 
    1 0 0 0 0 0 0  13 13
    2 0 0 0 0 8 8  13 13
    3 0 0 0 6 8 8  13 14
    4 0 0 0 6 8 12 13 12

    dp[0] => 0
    dp[1] => 0
    dp[2] => 0
    dp[3] => 6
    dp[4] => 8
    dp[5] => 12
    dp[6] = 13

    dp[7][1] = max( dp[7-1][1-1] + v[2], dp[7][1-1] )
    => 7의 총 무게를 담을 수 있을때, 무게가6, 1 인 물건 2개를 담냐, 무게가7 한개를 담냐
    => (w[1], v[1]) => (6,13) 이기때문에 6,1 로 나눠볼 생각을 할 수 있다

    (w[2], v[2]) = (4,8)
    dp[7][2] = max( dp[7-4][2-1] + v[2], dp[7][2-1] )

    (w[3], v[3]) = (3,6)
    dp[7][3] = max( dp[7-3][3-1] + v[3], dp[7][3-1] )
    => max( dp[4][2] + v[3] , dp[7][2] )
    => 8 + 6, 13 => 14가 더 크다

    dp[i][0] = 0

    // 현재 무게보다 더 무거운 물건인경우, 이전 메모이제이션 값을 가져온다
    dp[i][n] > w[n] => dp[i][n-1]

    // 현재 물건을 담을 수 있는 경우
    max( dp[i-w[]] )

    dp[최대무게][idx]
*/

import java.io.*
import java.util.StringTokenizer
private lateinit var bagDp : Array<Array<Int>>
private lateinit var wArr : Array<Int>
private lateinit var vArr : Array<Int>

fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine())
    val size = st.nextToken().toInt()
    val maxWeight = st.nextToken().toInt()

    bagDp = Array(maxWeight+1) { Array(size+1){-1} }
    for(i in 0 until maxWeight + 1){
      bagDp[i][0] = 0
    }

    wArr = Array(size+1){0}
    vArr = Array(size+1){0}
    for(i in 1 until size+1){
        st = StringTokenizer(br.readLine())
        wArr[i] = st.nextToken().toInt()
        vArr[i] = st.nextToken().toInt()
    }

    getBagDp(maxWeight, size)

    var max = Int.MIN_VALUE
    for(i in 1 until size+1){
        max = Math.max(bagDp[maxWeight][i], max)
    }
    bw.write("$max\n")
    
    bw.flush()
    bw.close()
    br.close()
}

private fun getBagDp(w : Int, i : Int) : Int{
    if(bagDp[w][i] >= 0){
      return bagDp[w][i]
    }
    // 현재 최대무게(w) 보다 현재물건의 무게(wArr[i])가 큰경우 
    bagDp[w][i] = if( w < wArr[i] ){
        getBagDp(w, i-1) // 이전 idx dp 값을 가져온다  
    }
    // 현재 최대무게(w) 와 현재 물건의 무게가 같은경우
    else if( w == wArr[i] ){
        Math.max(getBagDp(w, i-1), vArr[i]) 
        // 이전 idx dp 값과 현재 물건의 가치중 높은것 저장
    }
    // 현재 최대무게(w)가 현재 물건의 무게보다 큰 경우
    else {
        // 최대무게 - 현재무게를 뺀 무게의 dp + 지금 물건의 가치
        // 이전 idx 의 값
        Math.max(getBagDp(w-wArr[i], i-1) + vArr[i], getBagDp(w, i-1))
    }
    return bagDp[w][i]
}