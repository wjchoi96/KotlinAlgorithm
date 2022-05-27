/*
    바킹독님 알고리즘 강의 중 dp단원 연습문제
    sliver 1

    1로 만들기2

    #dp에서 경로를 추적하는 방법
    단순히 값만 출력하는게 아닌, 거쳐온 과정을 출력하라 한다
    이런 문제를 해결하기 위해서는 테이블을 채울 때 추가적인 정보를 어딘가에 기입해야한다
    => 여기까지만 보고 시도

    정수 X에 사용할 수 있는 연산은 3가지가 존재
    1. X가 3으로 나누어 떨어지면 3으로 나눈다
    2. X가 2로 나누어 떨어지면 2로 나눈다
    3. 1을 뺀다

    정수 N이 주어졌을때, 위와 같은 연산 3개를 적절히 사용해서 1을 만드려 한다
    연산을 사용하는 횟수의 최소값을 출력

    1<= n <= 10^6

    정답이 여러개인 경우, 아무거나 출력

    1. 연산을 하는 횟수의 최솟값 출력
    2. n을 1로 만드는 방법에 포함되어있는 수를 공백으로 구분해서 출력
    => 진행 루트를 출력

    0.5초
*/
/*
    1. 테이블 정의하기
    dp[n]: n을 1로 만드는 최소 횟수

    2. 점화식 구하기
    - 3으로 나눌수 있다면: dp[n] = dp[n/3]+1
    - 2로 나눌 수 있다면: dp[n] = dp[n/2]+1
    - 둘다 못한다면: dp[n] = dp[n-1]+1

    3. 초기값 설정
    dp[1] = 0
*/
/*
    경로 구하기
    1. 경로 테이블
    dpLog: Array<StringBuilder>
    dp[n] = n을 1로 만들기 위해 가는 경로

    => 2번 시간 초과 후 변경

    dpLog: Array<Int>}
    dpLog[n] = n을 1로 만드는 최소값으로 가려면 어느 숫자로 가야하는지 기록
*/
/*
    제출
    1. 시간초과
    - 매번 새로운 StringBuilder 를 만드는 것은 O(n)의 시간복잡도가 소모되는일이라 문제가 되는것같다
    - log 를 bottom-top 방식으로 쌓는걸로 이해했어서 그렇게 진행하였는데, top-bottom으로 log를 쌓는 방식이라 새로운 StringBuilder를 생성하지 않아도 된다

    2. 시간초과
    - log 테이블 변경

    3. 성공
*/
private lateinit var dp: Array<Int>
private lateinit var dpLog: Array<Int>
fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    dp = Array(n+1){-1}
    dpLog = Array(n+1){-1}
    dp[1] = 0
    dpLog[0] = 0
    dpLog[1] = 0    
    bw.write("${getRes(n)}\n")
    
    var x = n
    bw.write("$x ")
    while(dpLog[x]>0){
        x = dpLog[x]
        bw.write("$x ")
    }
   
    bw.flush()
    br.close()
    bw.close()
}
private fun getRes(x: Int): Int {
    if(dp[x] != -1) return dp[x]
    dp[x] = getRes(x-1)+1
    var saveLog = x-1
    if(x%3==0){
        if(getRes(x/3)+1 < dp[x]){
            dp[x] = getRes(x/3)+1
            saveLog = x/3
        }
    }
    if(x%2==0){
        if(getRes(x/2)+1 < dp[x]){
            dp[x] = getRes(x/2)+1
            saveLog = x/2
        }
    }
    dpLog[x] = saveLog
    return dp[x]
}