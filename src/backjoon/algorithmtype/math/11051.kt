/*
    바킹독님 알고리즘 강의 중 수학 단원 이항계수 연습문제
    sliver 1

    이항 계수 2

    자연수 N과 정수K가 주어졌을떄 이항 계수 (N,K)를 10007로 나눈 프로그램을 작성하시오 구하는 프로그램을 작성하시오

    1초

    1<= N <= 1000
    0<= K <= N
*/
/*
    기존 팩토리얼 코드에 적용하기엔 10007 로 나눈 나머지들끼리의 나눗셈이 성립하지 않는다
    방법은 있다고하는데 수학적이다

    nCk = (n-1)C(k-1) + (n-1)Ck 라는 성질을 이용하자
    dp[n][k]= iCj 라고 두면
    dp[n][k] = dp[n-1][k-1] + dp[n-1][k] 이다

    int overflow에 주의해서 코드를 작성

    초기값 설정을 모르겠네;
*/
/*
    제출
    - 성공
    - 수학적인것에 대한 이해가 부족해서 이항계수 문제는 너무 스트레스받는다
    - 공식은 보고 풀었다
*/

fun main(args: Array<String>){
    Solution11051().solve()
}
class Solution11051 {
    private lateinit var dp: Array<Array<Int>>
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()
    
        //input data
        var (n,k) = br.readLine().split(' ').map{it.toInt()}
        // dp = Array(n+1){Array(k+1){-1}}
        dp = Array(1001){Array(1001){0}}

        for(i in 1..1000){
            dp[i][0] = 1
            dp[i][i] = 1
            for(j in 1 until i){
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j])%10007
            }
        }

        bw.write("${dp[n][k]}\n")

        bw.flush()
        br.close()
        bw.close()
    }

}