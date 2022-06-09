/*
    solved.ac 클래스 1_1 미해결 문제
    bronze 5

    검증수

    컴퓨터마다 6자리의 고유번호를 매긴다
    처음 5자리에는 00000 부터 99999까지의 수 중 하나가 주어지며
    6번째 자리에는 검증수가 들어간다

    검증수의 고유번호는 처음 5자리에 들어가는 5개의 숫자를 각각 제곱한 수의 
    합을 10으로 나눈 나머지 이다

    1초
*/
/*
    제출
    1. 성공
*/

fun main(args: Array<String>){
    Solution2475().solve()
}
class Solution2475 {
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()

        val arr = br.readLine().split(' ').map{it.toInt()}
        var res = 0
        for(v in arr){
            res += v*v
        }
        bw.write("${res%10}\n")

        bw.flush()
        bw.close()
        br.close()
    }
}