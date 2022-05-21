/*
    바킹독님 알고리즘 강의 중 Dp단원 연습문제
    sliver 3
    
    1로 만들기

    1. dp
    2. bfs
    방식으로 풀어보자

    정수X에 사용할 수 있는 연산은 세가지이다
    1. X가 3으로 나누어 떨어지면, 3으로 나눈다
    2. X가 2로 나누어 떨어지면 2로 나눈다
    3. 1을 뺀다
    
    3연산을 사용해서 1을 만드려고 한다
    연산을 사용하는 횟수와 최소값을 구하시오

    1<= x <= 10^6
    0.15 초
*/
/*
    n=16 으로 시작한다면
    dp[16] = 0

    dp[8] = dp[16]+1 (2번 연산)
    dp[15] = dp[16]+1 (3번 연산)

    dp[4] = dp[8]+1 (2번 연산)
    dp[7] = dp[8]+1 (2번 연산)

    ... 진행해 나가다가 1의 결과를 출력

    base condition?
    => 음수가 될때

    dp의 범위 => n이 최대값이 되도록 하면된다
    => 주어진 연산이 모두 감소연산이기 때문

    
    => 1번 제출 이후 구현 중 이상함을 느껴서 변경
    1부터 시작해서 n이 나올때까 뻗어나가는 형식으로 변경
    => base condition: n보다 커졌을때 
    => 이때 리턴값?

    => 3번 제출 이후 이상함을 느껴서 예전에 풀었을때 접근방식을 살짝만 확인하고 옴
    : dp[1]이 현재 주어진 n에서 1을 만드는 최소값이 아니라,
    n이 1이 주어졌을때 1을 만드는 최소값이다

    dp[1] = 1
    dp[2] = dp[1]+1 (-1 or /2)
    dp[3] = dp[1]+1 (/3)
    dp[4] = dp[3]+1 or dp[2]+1 
    dp[5] = dp[4]+1
    dp[6] = dp[3]+1 or dp[5]+1 or dp[2]+1

    이렇게 나아가서 dp[n]을 구한다
*/
/*
    제출
    1. 틀렸습니다(3%)
    - base condition 리턴값 변경

    2. 시간초과(1%)

    3. 시간초과
    - 이렇게 접근해서는 안된다
    - dp는 base condition(표현이 맞는지는 모르겠지만, 값을 지니고 있는 dp)을 중심으로 차근차근 펼쳐가는 모양새가 되어야한다

    4. 런타임에러: ArrayIndexOutOfBounds(90% 쯤)
    - n이 1이 주어질떄 init dp 에서 문제가 발생

    5. 정답
*/
/*
    개선
    1. 어떻게 이런 접근방식에 도달했는지는 모르겠지만, 숏코딩을 통해 개선된 형태의 코드를 발견
    - 이해가 어려운 코드는 아니기에 바로 적용은 가능했으나, 이러한 접근방식을 생각해 내는것이 중요할듯

    2. bfs방식으로 풀이
    - 개선은 아니지만, 다양한 방식의 풀이에 도전
    - 성공

*/
/*
    바킹독님 접근방식

    1. 테이블 정의하기
    - dp[i]를 1로 만들기 위해 필요한 연산 사용 횟수의 최솟값
    => dp[12]를 찾는다면?
    2. 점화식 찾기
    - 3으로 나누거나, 2로 나누거나, 1을 빼거나
    => dp[12] = min(dp[4]+1, dp[6]+1, dp[11]+1)
    3. 초기값 정의하기
    - dp[1] = 0
*/
import java.util.Queue
import java.util.LinkedList
private lateinit var dp: Array<Int> // use at solve1, solve2
private lateinit var disit: Array<Int> // use at bfs
fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    
    val n = br.readLine().toInt()
    disit = Array(n+1){-1}

    fun bfs(x: Int=1){
        val queue : Queue<Int> = LinkedList()
        queue.offer(x)
        disit[x] = 0
        while(!queue.isEmpty()){
            val node = queue.poll()
            val d = arrayOf(node+1, node*2, node*3)
            for(i in 0 until 3){
                val newNode = d[i]
                if(newNode>=disit.size){
                    continue
                }
                if(disit[newNode] != -1){
                    continue
                }
                queue.offer(newNode)
                disit[newNode] = disit[node]+1
            }
        }
    }
    bfs()
    println("${disit.toList()}")
    bw.write("${disit[n]}\n")
    bw.flush()
    br.close()
    bw.close()
}

// 숏코딩을 통해 발견한 코드
private fun solve2(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    
    val n = br.readLine().toInt()
    dp = Array(n+1){-1}
    dp[1] = 0
    for(i in 2 until n+1){
        dp[i] = dp[i-1]+1
        if(i%3 == 0) dp[i] = Math.min(dp[i], dp[i/3]+1)
        if(i%2 == 0) dp[i] = Math.min(dp[i], dp[i/2]+1)
    }
    println("${dp.toList()}")
    bw.write("${dp[n]}\n")

    bw.flush()
    br.close()
    bw.close()
}

private fun solve1(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    
    val n = br.readLine().toInt()
    dp = Array(n+1){-1}
    fun initDp(size: Int = n){
        dp[1] = 0
        if(size>=2) dp[2] = 1
        if(size>=3) dp[3] = 1
    }
    initDp()

    fun getMinDp(x: Int = n): Int{
        if(dp[x]!=-1){
            return dp[x]
        }
        val dp3 = if(x%3==0)getMinDp(x/3) else Int.MAX_VALUE
        val dp2 = if(x%2==0)getMinDp(x/2) else Int.MAX_VALUE
        val dp1 = if(x-1>0)getMinDp(x-1) else Int.MAX_VALUE
        dp[x] = Math.min(Math.min(dp3, dp2), dp1) + 1
        return dp[x]
    }

    getMinDp()
    println("${dp.toList()}")
    bw.write("${dp[n]}\n")

    bw.flush()
    br.close()
    bw.close()
}
