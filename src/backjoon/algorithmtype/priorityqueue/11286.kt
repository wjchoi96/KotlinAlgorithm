/*
    바킹독님 알고리즘 강의 중 우선순위 큐 단원 연습문제
    sliver 1

    절댓값 힙

    1. 배열에 정수x(x!=0)을 넣는다
    2. 배열에서 절댓값이 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다
    절대값이 가장 작은 값이 여러개일땐, 가장 작은 수를 출력하고, 제거

    연산의 개수 N
    1<= N <= 100,000
    -2^31 <= x <= 2^32

    x가 0이 아니라면 배열에 추가
    x가 0이라면 배열에서 가장 작은 값 출력
    배열이 비어있는데 값을 출력하라 할땐 0 출력
*/
/*
    제출
    1. 성공
*/
/*
    Comparator 이 헷갈린다 다시 정리

    comparator, comparable 처리
    https://st-lab.tistory.com/243

    compareable 은 자기자신과 인자로 들어오는 인자의 비교
    comparator 은 2개의 인자를 서로 비교

    리턴값은 1, 0, -1을 주로 사용
    A가 B보다 클때, 해당사실을 알리고싶다면 1 리턴
    A==B => 0
    A<B => -1
    를 보통 return A-B로 나타낸다
    => 오름차순 기준이다
    => 내림차순으로 하고 싶다면 B-A 를 하면 된다

    자기 자신을 기준으로 삼아 대소관계를 파악하는 것 / Comparator 은 인자로 전달되는 a,b의 대소관계 파악
    => 자기 자신이 비교대상보다 크다면 양수
    => 자기 자신이 비교대상과 같다면 0
    => 자기 자신이 비교대상보다 작다면 음수
    
    sortWith 의 인자로는 compareBy를 사용해 여러개의 comparable을 보내거나
    compartor을 받는다

    기본은 오름차순이며, 내림차순으로 하고싶다면 반대로 해주면 된다
*/

import java.util.PriorityQueue
fun main(args: Array<String>){
    Solution11286().solve()
}
class Solution11286 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        // val queue = PriorityQueue<Int>(Comparator { a,b ->
        //     if(Math.abs(a) == Math.abs(b)){
        //         a-b
        //     }else{
        //         Math.abs(a)-Math.abs(b)
        //     }
        // })
        val queue = PriorityQueue<Int>(compareBy(
            {Math.abs(it)}, 
            {it}
        ))
        repeat(br.readLine().toInt()){
            val x = br.readLine().toInt()
            when(x){
                0 -> bw.write("${queue.poll() ?: 0}\n")
                else -> {
                    queue.offer(x)
                    println("${queue}")
                }
            }
        }
    
        bw.flush()
        bw.close()
        br.close()
    }
}
