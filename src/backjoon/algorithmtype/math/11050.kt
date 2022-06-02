/*
    바킹독님 알고리즘 강의 중 수학 단원 이항계수 연습문제
    bronze 1

    이항 계수 1

    자연수 N과 정수K가 주어졌을떄 이항 계수 (N,K)를 구하는 프로그램을 작성하시오

    - 다짜고짜 이항계수를 구하라하네?
    - b가 n개 있을 때 이들중에 r개를 뽑는다는 것 => 이게 이항계수
    - 순서를 고려하여 뽑는것인듯?

    - 바킹독님은  문제를 보면 nCk를 계산하도록 하고 있습니다. 라고 하시네

    1초

    1<= N <= 10
    0<= K <= N
*/
/*
    제출
    1. 성공
*/

fun main(args: Array<String>){
    Solution11050().solve()
}
class Solution11050 {
    private lateinit var dp: Array<Int>
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()
    
        //input data
        var (n,k) = br.readLine().split(' ').map{it.toInt()}
        dp = Array(n+1){-1}
        dp[0] = 1
        dp[1] = 1
        factorial(n)
        dp.forEach {
            bw.write("$it\n")
        }
        bw.write("${dp[n]} / (${dp[k]} * ${dp[n-k]})\n")
        val res = dp[n] / (dp[k] * dp[n-k])
        bw.write("$res\n")

        bw.flush()
        br.close()
        bw.close()
    }
    private fun factorial(n: Int): Int {
        if(dp[n]!= -1) return dp[n]
        dp[n] = n*factorial(n-1)
        return dp[n]
    }
}