/*
    바킹독님 알고리즘 강의 중 dp단원 연습문제
    sliver 3

    1,2,3 더하기

    정수 4를 1,2,3의 합으로 나타내는 방법은 총 7가지가 존재한다
    합을 나타낼때는 수를 1개 이상 사용해야한다

    1+1+1+1
    1+1+2
    1+2+1
    2+1+1
    2+2
    1+3
    3+1

    정수 n이 주어졌을때, n을 1,2,3의 합으로 나타내는 방법의 수를 구하시오

    0<= n <= 11
    1초
*/
/*
    범위가 작아서 백트래킹으로도 가능하겠지만, dp단원이니 dp로 구현

    1. 테이블 정의하기
    dp[n]: n을 1,2,3의 합으로 나타내는 수의 합
    2. 점화식 찾기
    dp[1]: 1 [1]
    dp[2]: 2 [1,1], [2]
    dp[3]: 4 [1,1,1], [1,2], [2,1], [3]
    dp[4]: 7 [1,1,1,1], [1,1,2], [1,2,1], [2,1,1], [2,2], [1,3], [3,1]

    바킹독님 방법(이걸 어떻게 생각해냈지)
    dp[4] = ?
    [1,1,1,1], [3,1], [2,1,1], [1,2,1] => dp[3]의 방법에 각+1 한것
    [1,1,2], [2,2] => dp[2]의 방법에 각 +2를 한것
    [1,3] => dp[1]의 방법에 +3을 한것

    dp[4] = dp[1]+dp[2]+dp[3] 

    3. 초기값 정하기
    dp[1]: 1 [1]
    dp[2]: 2 [1,1], [2]
    dp[3]: 4 [1,1,1], [1,2], [2,1], [3]

    dp[i] = dp[i-1] + dp[i-2] + dp[i-3] 이니 3개는 초기값으로 정해져야 한다
*/
/*
    제출
    1. 런타임에러(ArrayIndexOutOfBounds)
    - init dp 코드 범위 문제
    - 0인 경우도 추가

    2. 성공

    3. 성공
    - 재귀가 아닌 for문으로 접근
*/

private lateinit var dp: Array<Int>
private var n: Int = 0
fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    repeat(br.readLine().toInt()){
        n = br.readLine().toInt()
        initDp(n)
        bw.write("${getResult2(n)}\n")
    }

    bw.flush()
    br.close()
    bw.close()
}
private fun initDp(size: Int){
    dp = Array(n+1){-1}
    dp[0] = 0
    if(size>=1)dp[1] = 1
    if(size>=2)dp[2] = 2
    if(size>=3)dp[3] = 4
}
fun getResult(x: Int): Int{
    if(dp[x] != -1){
        return dp[x]
    }
    dp[x] = getResult(x-1) + getResult(x-2) + getResult(x-3)
    return dp[x]
}
fun getResult2(x: Int): Int{
    for(i in 4..x){
        dp[i] = dp[i-1]+dp[i-2]+dp[i-3]
    }
    return dp[x]
}