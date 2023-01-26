/*
    백준 2252
    바킹독 위상정렬 단원 연습문제
    https://www.acmicpc.net/problem/2252

    gold 3

    N명의 학생들을 키 순서대로 줄을 세우려 함
    - 두 학생의 키를 비교하는 방법을 사용
    - 일부 학생들의 키만을 비교해봄

    1 ≤ N ≤ 32,000
    1 ≤ M ≤ 100,000 => 키를 비교한 회수

    키를 비교한 두 학생의 번호 A, B가 주어짐
    - A가 B의 앞에 서야함
    - A에서 B로 간선이 향함

    답이 여러개인 경우, 아무거나 출력
*/
/*
    학생들을 정점, 키를 비교한것을 간선으로 설정하여 위상정렬 진행
    N은 Node의 수
    M은 간선의 수

    0. 그래프에 정점, 간선 정보 저장
    1. indegree 테이블을 완성
    2. indegree가 0인 정점을 Queue에 넣음
    3. pop된 정점의 outdegree를 지우는것을, indegree 테이블에 적용
    4. indegree가 0이된 정점을 Queue에 넣음

    #제출
    1. 성공

    #숏코딩
    
*/

import java.util.Queue
import java.util.LinkedList
fun main(){
    Solution2252().solve()
}
class Solution2252 {
    private lateinit var graph: Array<ArrayList<Int>>
    private lateinit var indegreeTable: Array<Int>

    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (n, m) = br.readLine().split(' ').map{ it.toInt() }
        graph = Array(n+1){ ArrayList() }
        indegreeTable = Array(n+1) { 0 }
        repeat(m) {
            br.readLine().split(' ').map{ it.toInt() }.let {
                graph[it[0]].add(it[1])
                indegreeTable[it[1]]++
            }
        }

        val queue: Queue<Int> = LinkedList()
        val resultQueue: Queue<Int> = LinkedList()
        for(i in 1 until n+1) {
            if(indegreeTable[i] == 0) queue.offer(i)
        }

        while(!queue.isEmpty()){
            val cur = queue.poll()
            resultQueue.offer(cur)
            for(nxt in graph[cur]) {
                indegreeTable[nxt]--
                if(indegreeTable[nxt] == 0){
                    queue.offer(nxt)
                }
            }
        }

        val sb = StringBuilder()
        for(i in resultQueue.toList()){
            sb.append("$i ")
        }
        bw.write("${sb.toString()}\n")
    
        bw.flush()
        bw.close()
        br.close()
    }
}