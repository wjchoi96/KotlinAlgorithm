/*
    백준 1107
    solved.ac class 3 
    https://www.acmicpc.net/problem/1107

    gold 5

    리모콘의 일부 숫자 버튼이 고장남
    0에서 9, +와 -버튼이 존재
    +를 누르면 현재 채널에서 +1
    -를 누르면 현재 채널에서 -1

    채널0에서 -를 누른 경우에는 채널이 변하지 않음
    채널은 무한대로 존재

    N번의 채널로 이동하려 함
    어떤 버튼이 고장났는지 주어졌을때, 채널 N으로 이동하기 위해 버튼을 최소 몇번 눌러야 하는지 프로그램을 작성

    현재 채널은 100번 채널

    0 ≤ N ≤ 500,000 => 이동하려는 채널
    0 ≤ M ≤ 10 => 고장난 버튼의 개수
*/
/*
    1. 이동하려는 채널가 최대한 가깝게 이동
    2. +, -로 이동

    채널의 범위가 500000
    두배해서 1000000(100만)의 수를 모두 검사해도 지장없는 범위

    이떄, 고장난 숫자버튼의 유무를 contains를 사용한다면 시간복잡도가 늘어나기에, 
    고장난 숫자버튼을 포함하는 수는 검사하지 않도록 함

    #제출
    1. 틀렸습니다(1%)
    - 100번채널에서 시작하므로, 100에서 +-만 눌러서 채널에 도착하는 경우의 수를 놓침
반례
101
0
=> 1

    2. 틀렸습니다(4%)
    - 0이 고장나면 numbers에 0이 사라지면서 1의자리, 10의자리 등 앞자리에 0이 들어와서 완성되는 작은 자리의 수들을 체크하지 못함
    - 완탐 방식을 변경해야 할듯
    - https://moonsbeen.tistory.com/64 참고
반례
0
2
0 1
==> 3

    3.성공

    #반례모음
    https://www.acmicpc.net/board/view/109610

*/
import java.util.StringTokenizer
fun main(){
    Solution1107().solve()
}
class Solution1107 {
    private val SIZE = 999999 // 리모컨의 수는 9까지이며, 채널의 최대값은 500000
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
    
        val n = br.readLine().toInt()
        val breakNumbers = Array(10){false}
        val m = br.readLine().toInt()
        if(m != 0){
            val st = StringTokenizer(br.readLine())
            repeat(m) {
                breakNumbers[st.nextToken().toInt()] = true
            }
        }

        var min = Math.abs(n-100) // 100번채널에서 시작하므로, 100에서 +-만 눌러서 채널에 도착하는 경우의 수
        for(i in 0 until SIZE + 1){
            val str = i.toString()
            val len = str.length
            var isBreak = false
            for(s in str){
                if(breakNumbers[s.toString().toInt()]) {
                    isBreak = true
                    break
                }
            }
            if(isBreak) continue
            min = Math.min(min, Math.abs(n-i) + len)
        }

        bw.write("$min\n")

        bw.flush()
        bw.close()
        br.close()
    }
}