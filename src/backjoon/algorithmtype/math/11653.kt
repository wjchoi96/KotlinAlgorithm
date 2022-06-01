/*
    바킹독님 알고리즘 강의 중 수학 단원 소인수분해 문제
    sliver 5

    소인수분해

    정수 n이 주어졌을때 소인수분해를 진행
    오름차순으로 출력.
    N이 1인 경우는 아무것도 출력하지 않는다

    1초
    1<= n <= 10,000,000
*/
/*
    제출
    1. 성공
*/
fun main(args: Array<String>){
    Solution11653().solve()
}
class Solution11653 {
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()
    
        //input data
        var n = br.readLine().toInt()
        var i = 2
        while(n!=1){
            if(i*i > n){
                bw.write("$n\n")
                break
            }
            while(n%i==0){
                n /= i
                bw.write("$i\n")
            }
            i++
        }

        bw.flush()
        br.close()
        bw.close()
    }
}