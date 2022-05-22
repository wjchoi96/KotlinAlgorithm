/*
    바킹독님 알고리즘 강의 중 dp 단원
    sliver 1

    RGB거리
    
    RGB거리에는 집이 N개 있다
    거리는 선분으로 나타낼 수 있고, 1번집부터 N번집이 순서대로 있다

    집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다
    각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을때, 
    아래 규칙을 만족하며 모든 집을 칠하는 비용의 최소값을 구하시오

    - 1번집의 색은 2번집의 색과 같지 않아야 한다
    - N번 집의 색은 N-1번 집의 색과 같지 않아야 한다
    - i(2<= i <=n-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다

    => 인접한 집들끼리 색이 달라야 한다

    2<= N <= 1000
    집을 칠하는 비용은 1000보다 작거나 같은 자연수이다
*/
/*
    0. data
    color[n][c] = Array(n+1){Array(3){0}} 
    n: 집의 번호
    c: color(R,G,B 중 하나)

    1. 테이블 정의하기
    - dp[n][c]: n번집을 c색으로 칠했을때 비용의 최소값 (x)

    2. 점화식 찾기
    dp[0] = 0 // 사용하지 않는 값
    dp[1][c] == color[1][c]
    dp[2][c]: dp1의 c를 제외한 나머지 두개의 color중 최소값

    dp[n][c] = dp[n-1][dp[n-1]의 c를 제외한 나머지 두개의 color중 최소값]

    3. 초기값 정하기
    dp[0] = 0 // 사용하지 않는 값
    dp[1][c] == color[1][c]

    4. 출력
    dp[n][c] 3개중 최소값
*/
/*
    제출
    1. 성공
    - 이전에 풀어봤던 경험 덕분인지 쉽게 접근하고 쉽게 구현하였다
    - bottom-top 으로 구현했으니 top-bottom도 구현해보자

    2. 성공
    - top-bottom 구현
*/
private lateinit var color: Array<Array<Int>>
private lateinit var dp: Array<Array<Int>>
private val R = 0
private val G = 1
private val B = 2
fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    //input data
    val n = br.readLine().toInt()
    color = Array(n+1){Array(3){0}}
    repeat(n){
        color[it+1] = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
    }
    //init dp
    dp = Array(n+1){Array(3){-1}}
    repeat(3){
        dp[0][it] = 0
        dp[1][it] = color[1][it]
    }

    //top-bottom dp
    repeat(3){
        getMinColorCost(n, it)
    }

    //ouput
    val res = Math.min(
        dp[n][R],
        Math.min(
            dp[n][G],
            dp[n][B]
        )
    )
    bw.write("$res\n")

    bw.flush()
    br.close()
    bw.close()
}
//top-bottom
private fun getMinColorCost(x: Int, c: Int): Int{
    if(dp[x][c] != -1) return dp[x][c]
    when(c){
        R -> dp[x][c] = Math.min(
            getMinColorCost(x-1, G),
            getMinColorCost(x-1, B)
        ) + color[x][c]
        G -> dp[x][c] = Math.min(
            getMinColorCost(x-1, R),
            getMinColorCost(x-1, B)
        ) + color[x][c]
        B -> dp[x][c] = Math.min(
            getMinColorCost(x-1, G),
            getMinColorCost(x-1, R)
        ) + color[x][c]   
    }
    return dp[x][c]
}


//bottom-top
private fun solve1(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    //input data
    val n = br.readLine().toInt()
    color = Array(n+1){Array(3){0}}
    repeat(n){
        color[it+1] = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
    }
    //init dp
    dp = Array(n+1){Array(3){-1}}
    repeat(3){
        dp[0][it] = 0
        dp[1][it] = color[1][it]
    }

    //bottom-top dp
    for(i in 1..n){
        dp[i][R] = Math.min(dp[i-1][G], dp[i-1][B]) + color[i][R]
        dp[i][G] = Math.min(dp[i-1][R], dp[i-1][B]) + color[i][G]
        dp[i][B] = Math.min(dp[i-1][R], dp[i-1][G]) + color[i][B]
        println("[$i] => R: ${dp[i][R]}, G: ${dp[i][G]}, B: ${dp[i][B]}")
    }

    //ouput
    val res = Math.min(
        dp[n][R],
        Math.min(
            dp[n][G],
            dp[n][B]
        )
    )
    bw.write("$res\n")

    bw.flush()
    br.close()
    bw.close()
}