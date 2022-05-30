/*
    바킹독님 알고리즘 강의 중 그리디 단원
    sliver3

    동전0

    준규가 가지고 있는 동전은 총 N종류이고, 각각의 동전을 매우 많이 가지고 있다
    동전을 적절히 사용해서 그 가치의 합을 k로 만드려고한다
    이때 필요한 동전 개수의 최소값을 구하시오

    1<= n <= 10
    1<= k <= 100000000


    둘째줄부터 동전의 가치 a[]가 오름차순으로 주어진다
*/
/*
    - 일반적인 접근방식
    : 백트래킹? 브루트포스 해야하나? 이게 더 찾기 힘든거같네

    1. 관찰을 통해 탐색 범위를 줄이는 방법을 찾는다
    - 동전의 가치가 제일 큰 순으로 나누고, 나머지를 그 다음 큰 동전으로 나눈다
    - A[1]이 무조건 1이니 무조건 나누어 떨어진다
    - 반복
*/
/*
    제출
    1. 성공
*/
/*
    바킹독님의 그리디 증명
    
    동전개수를 최소화 하려면 500원 동전을 최대한 많이 사용해야한다

    -보조정리
    1. 동전을 최소로 소모하면서 물건값을 지불하려면 10/100 원 동전은 4개 이하, 50원 동전은 1개 이하로 사용해야한다
    - 귀류법으로 증명 가능
    (귀류법: 명제가 거짓이라고 가정할 때 모순이 발생함을 보이는 증명법)
    
    증명
    - 10/100원 동전을 5개 이상 사용 -> 50/500원 동전으로 대체
    - 50원 동전을 2개 이상 사용 -> 100원 동전으로 대체
    : 보조정리 1은 참이다

    귀류법 증명 흐름
    - 보조정리 1이 거짓이라고 가정
    => 10/100 원 동전을 5개 이상, 50원 동전을 2개 이상 사용해서 동전을 최소로 소모할 수 있다
    => 50/500 원 동전이나 100원 동전으로 대체하면 동전의 숫자가 줄어드는것을 확인 가능
    => 모순이 일어났으니 보조정리 1은 참

    반례
    보조징리1의 증명과정은 동전간의 배수 관계가 성립하여 가능. 배수관계가 성립하지 않을 때의 반례를 고민
    - 동전 1,9,10원이 있다면 18의 경우 정답은 9원짜리 2개이지만, 그리디로 접근할시 10원 1개, 1원짜리 9개가 될것이다 

    !!비슷한 문제일지라도 사소한 조건에 따라 확 달라질 수 있는 유형

*/
/*
    dp로도 가능하지만 현재 문제에서는 시간초과가 난다

    1. 테이블 정의
    dp[i]: i를 조합하는 동전의 최소 개수

    2. 점화식 찾기
    dp[i] = min(dp[i-coin[0]], dp[i-coin[1]]... dp[i-coin[n]])+1
    bottom-top 방식으로 O(nk) 가 된다

    3. 초기값 설정
    dp[coin[0]] = 1
    dp[coin[1]] = 1
    ..
    dp[coin[n-1]] = 1
*/

fun main(args: Array<String>){
    Solution11047().solve()
}
class Solution11047 {
    private lateinit var coins: Array<Int>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        var (n,k) = br.readLine().split(' ').map{it.toInt()}
        coins = Array(n){0}
        repeat(n){
            coins[it] = br.readLine().toInt()
        }

        var count = 0
        for(i in n-1 downTo 0){
            count += k/coins[i]
            k %= coins[i]
            if(k==0) break
        }
        bw.write("$count\n")

        
        bw.flush()
        bw.close()
        br.close()
    }

    /*
        dp[1] = 1
        dp[2] = 2
        dp[3] = 3
        ..
        dp[10] = 1 
        dp[11] = dp[10-1]+1
        dp[0] = 0
    */
    // 문제의 시간복잡도는 통과할 수 없지만, 구현은 해봄
    private lateinit var dp: Array<Int>
    fun solveDp(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        var (n,k) = br.readLine().split(' ').map{it.toInt()}
        coins = Array(n){0}
        dp = Array(k+1){-1}
        dp[0] = 0
        repeat(n){
            coins[it] = br.readLine().toInt()
            if(coins[it]<=k) dp[coins[it]] = 1
        }

        for(i in 2..k){
            if(dp[i] == 1) continue
            dp[i] = dp[i-coins[0]] 
            for(c in 1 until n){
                if(i-coins[c]<=0) break
                dp[i] = Math.min(dp[i], dp[i-coins[c]])
            }
            dp[i] += 1
        }

        bw.write("${dp[k]}\n")
        
        bw.flush()
        bw.close()
        br.close()
    }
}