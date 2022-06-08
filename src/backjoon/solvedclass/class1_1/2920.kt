/*
    solved.ac 클래스 1_1 미해결 문제
    bronze 2

    음계
    https://www.acmicpc.net/problem/2920

    다장조는 c d e f g a b C 총 8개 음으로 이루어져 있다
    이 문제에서 8개 음은 다음과 같이 숫자로 바꾸어 표현
    [1: c] [2: d] ... [8: C]

    1부터 8까지 차례로 연주한다면 ascending
    8부터 1까지 차례로 연주한다면 decending
    둘 다 아니라면 mixed 이다

    1초

    8개의 숫자가 주어진다
*/
/*
    제출
    1. 성공
*/

fun main(args: Array<String>){
    Solution2920().solve()
}
class Solution2920 {
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()

        val arr = br.readLine().split(' ').map{it.toInt()}
        var res = when(arr.first()){
            1 -> "ascending"
            8 -> "descending"
            else -> "mixed"
        }
        for(i in 0 until arr.size-1){
            if(Math.abs(arr[i]-arr[i+1]) != 1){
                res = "mixed"
                break
            }
        }
        bw.write("$res\n")

        bw.flush()
        bw.close()
        br.close()
    }
}