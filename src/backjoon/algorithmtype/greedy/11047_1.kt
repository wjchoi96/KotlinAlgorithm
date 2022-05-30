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
}