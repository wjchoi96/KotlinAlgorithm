/*
    백준 1516
    네이버 카페 문제 50선 중 위상정렬 유형
    https://www.acmicpc.net/problem/1516

    gold 3

    세준크래프트를 개발
    - 종족별 균형
    - 전체 게임 시간 조절

    게임 플레이에 들어가는 시간은 상황에 따라 다를 수 있기에, 모든 건물을 짓는데 걸리는 최소의 시간을 이용해 근사
    - 어떤 건물을 짓기 위해서 다른 건물을 먼저 지어야 할 수 있음
    - 여러개의 던물을 동시에 지을 수 있음
    - 자원은 무한
    - 건물을 짓는 명령을 내리기까지는 시간이 걸리지 않음

    1 ≤ N ≤ 500 => 건물의 종류 수
    1 <= 건설시간 <= 100000
    모든 건물을 짓는것이 가능한 입력만 주어짐
    건물의 번호는 1부터 N


    N개의 줄에 건물 건설 시간, 선행 건물 번호들이 주어짐. 
    각 줄은 -1로 끝남

    N개의 각 건물이 완성되기까지 걸리는 최소 시간
*/
/*
    1. graph에 건물과, 선행건물 정보 저장
    1-1. 각 노드별 건설시간 배열 저장
    2. 위상정렬을 통해 순서를 도출(여러개가 나올 수 있음)
    3. 여러 결과 중, 최소 건설 시간을 출력

    풀이 접근은 블로그를 조금씩 참고
    https://steady-coding.tistory.com/86
    https://zoonvivor.tistory.com/96

    #모든 건물은 동시에 지어질 수 있음
    - A(10), B(20)을 지어야 C를 지을 수 있다면 C를 건설 시작하기까지 걸리는 시간은 20
    
    #우선순위큐
    - 우선순위큐를 사용해, 건설 시간이 짧은 건물부터 Queue에서 뽑아낸다면?
    - 동시에 건물을 지을 수 있기 때문에, 건설시간이 더 긴 간선만이 필요하게 됨(짧은건 어차피 무시됨)
    - 짧은거를 먼저 처리해버리고, 건설시간이 긴 간선들을 나중에 처리해서 덮어 씌워버리면 결과가 나옴
    - indegree가 0이 될때 times를 갱신해줌(실제로 지어지기 시작할때)

    #건설시간 구하는 방식
    - 각 정점을 짓기 시작할때까지 걸린 시간을 배열에 저장
    - indegree를 감소시킬때(간선이 지워질때마다) 선행 건물들의 건설 시간중 최대값을 구함
        - 동시에 건물이 지어질 수 있기 때문에, 긴 건설시간을 골라야 함

    #제출
    1. 틀렸습니다(8%)
    - 출력 형태 오류 수정

    2. 틀렸습니다(8%)
    - 초기에 indegree가 0인 item들을 queue에 넣어줘야하는데, 그냥 다 넣어버림

    3. 성공

    #우선순위 큐 방식 제출
    1. 틀렸습니다(8%)
    - 건물이 실제로 지어질때 tiems를 갱신

    2. 틀렸습니다(8%)
    - minHeap을 번호순으로 정렬하도록함;

    3. 성공

*/
/*
    피드백
    1. 알고리즘 유형을 실수로 확인했는데, DP가 있어서 DP에 너무 얽매임
    2. 모든 건물이 동시에 건설 가능하다에 숨은 의미를 찾지 못했음
    3. 고정관념을 버리고 넓게 생각해보자
*/

import java.util.Queue
import java.util.LinkedList
import java.util.PriorityQueue
fun main(){
    Solution1516().solve()
}
class Solution1516 {
    private lateinit var graph: Array<ArrayList<Int>>
    private lateinit var indegreeTable: Array<Int>
    private lateinit var times: Array<Int>
    private lateinit var build: Array<Int>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        graph = Array(n+1){ArrayList()}
        indegreeTable = Array(n+1){0}
        times = Array(n+1){0}
        build = Array(n+1){0}

        repeat(n) { idx ->
            br.readLine().split(' ').let {
                times[idx+1] = it[0].toInt()
                for(i in 1 until it.size-1) {
                    //idx+1건물을 짓기위해 선행되어야 하는 건물들. 간선이 idx+1로 향함
                    graph[it[i].toInt()].add(idx+1)
                    indegreeTable[idx+1]++
                }
            }
        }

        val queue: Queue<Int> = LinkedList()
        for(i in 1 until indegreeTable.size) {
            if(indegreeTable[i] == 0)
                queue.offer(i)
        }
        while(!queue.isEmpty()){
            val cur = queue.poll()
            for(nxt in graph[cur]) {
                indegreeTable[nxt]--
                build[nxt] = Math.max(build[nxt], build[cur] + times[cur]) // 건물 건설 시작까지 걸리는 시간
                if(indegreeTable[nxt] == 0){
                    queue.offer(nxt)
                }
            }
        }
        for(i in 1 until build.size) {
            bw.write("${build[i] + times[i]}\n")
        }
    
        bw.flush()
        bw.close()
        br.close()
    }

    //우선순위 큐 방식
    fun solve2(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        graph = Array(n+1){ArrayList()}
        indegreeTable = Array(n+1){0}
        times = Array(n+1){0}

        repeat(n) { idx ->
            br.readLine().split(' ').let {
                times[idx+1] = it[0].toInt()
                for(i in 1 until it.size-1) {
                    //idx+1건물을 짓기위해 선행되어야 하는 건물들. 간선이 idx+1로 향함
                    graph[it[i].toInt()].add(idx+1)
                    indegreeTable[idx+1]++
                }
            }
        }

        val minHeap = PriorityQueue<Int>(compareBy({times[it]}))
        for(i in 1 until indegreeTable.size) {
            if(indegreeTable[i] == 0)
                minHeap.offer(i)
        }
        while(!minHeap.isEmpty()){
            val cur = minHeap.poll()
            for(nxt in graph[cur]) {
                indegreeTable[nxt]--
                if(indegreeTable[nxt] == 0){
                    times[nxt] += times[cur]
                    minHeap.offer(nxt)
                }
            }
        }
        for(i in 1 until times.size) {
            bw.write("${times[i]}\n")
        }
    
        bw.flush()
        bw.close()
        br.close()
    }
}