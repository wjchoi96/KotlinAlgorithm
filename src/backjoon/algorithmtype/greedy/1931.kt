/*
    바킹독님 알고리즘 강의 중 그리디 단원 연습문제
    sliver 1
    그리디 파트에서 task scheduling problem이라는 이름으로 꼭 다루는 문제

    회의실 배정

    한개의 회의실이 있는데, 이를 사용하고자 하는 N개의 회의에 대하여 회의실 사용표를 만드려고 한다
    각 회의 i에 대해 시작시간과 끝나는 시간이 주어져있고
    각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자
    - 회의는 한번 시작하면 중단될 수 없다
    - 한 회의가 끝나는 동시에 다음 회의가 시작될 수 있다
    - 회의의 시작시간과 끝나는 시간이 같을 수 있다. 이 경우는 시작하자마자 끝나는것으로 생각하면 된다

    1<= n <=100000
    둘째 줄부터 N+1 줄까지 각 회의의 정보가 주어진다
    시작시간과 끝나는 시간은 2^31-1 보다 작거나 같은 자연수 또는 0이다
*/
/*
    회의가 빨리 끝나는 것을 우선으로 탐색한다
    첫번째 회의가 끝나고, 해당 시간 이후에 시작하는 회의 중 가장 빨리 끝나는 회의를 탐색한다
    남은 회의가 없을때까지 반복한다

    명제 증명
    1. 가장 빨리 끝나는 회의를 고르면 회의의 최대 개수를 고를 수 있다
    => 이것을 거짓이라 가정
    => 가장 빨리 끝나지 않는 회의를 골라도 최대 개수를 고를 수 있다
    안되지않나?
*/
/*
    제출
    1. 틀렸습니다(80% 쯤)
    - 같은 시간에 끝나는 회의의 경우를 체크 못함
    - 끝나는 시간이 동일하다면 시작하는 시간이 빠른 순으로 배치해야 하는 이유는 시작하자마자 끝나는 회의의 존재 때문
    - (2, 2), (1, 2) 이렇게 2개의 회의가 있는 경우를 고민

    2. 성공

*/

fun main(args: Array<String>){
    Solution1931().solve()
}

class Solution1931 {
    private lateinit var tasks: Array<Pair<Int, Int>>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
        var n = br.readLine().toInt()
        tasks = Array(n){0 to 0}
        repeat(n){
            br.readLine().split(' ').map{it.toInt()}.let{ t ->
                tasks[it] = t[0] to t[1]
            }
        }
        tasks.sortWith(compareBy(
            {it.second}, {it.first}
        )) // 가장 빨리 끝나는 순으로 정렬, 같은시간에 끝난다면 시작시간이 빠른 순으로 정렬

        var count = 0
        var runTask = 0 to 0
        for(task in tasks){
            if(task.first >= runTask.second){
                count++
                runTask = task
            }
        }
        bw.write("$count\n")
        
        bw.flush()
        bw.close()
        br.close()
    }
}