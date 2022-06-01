/*
    바킹독님 알고리즘 강의 중 수학 단원 연습문제
    sliver 4

    소수 찾기

    주어진 수 n개 중에서 소수가 몇개인지 출력하시오

    2초
    n <= 100

    1<= n[i] <= 1000
*/
/*
    제출
    1. 틀렸습니다(7%)
    - isPrime 함수에서 %연산 실수(둘이 순서를 바꿈)

    2. 성공
*/

fun main(args: Array<String>){
    Solution1978().solve()
}
class Solution1978 {
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()
    
        //input data
        val n = br.readLine().toInt()
        val arr = br.readLine().split(' ').map{it.toInt()}
        var count = 0
        for(i in arr){
            if(isPrime(i)) count++
        }
        bw.write("$count\n")
        bw.flush()
        br.close()
        bw.close()
    }

    private fun isPrime(n: Int): Boolean {
        if(n==1) return false
        var i = 2
        while(i*i<=n){
            if(n%i==0)return false
            i++
        }
        return true
    }
}