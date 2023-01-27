/*
    백준 1197
    바킹독님 알고리즘 강의 중 최소신장트리 단원 연습문제
    https://www.acmicpc.net/problem/1197
    
    gold 4

    그래프가 주어졌을때, 최소 스패닝 트리를 구하시오

    1 ≤ V ≤ 10,000 => 정점의 개수
    1 ≤ E ≤ 100,000 => 간선의 개수
    A번 정점과 B번 정점이 가중치 C인 간선으로 연결
    C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않음
    최소 스패닝 트리의 가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만 입력으로 주어짐

*/
/*
    1. Edge(cost, node, node) Class를 정의해 그래프를 작성
    2. 임의의 정점을 선택해 최소신장트리에 추가
    3. 우선순위큐에 최소신장트리의 정점과, 그렇지 않은 정점들을 연결하는 간선을 추가
    - cost를 기준으로 minHeap
    4. 우선순위큐에서 간선을 꺼내 3번을 반복
    5. 최소신장트리에 V-1개의 간선이 추가될때까지 반복

    #최소신장트리에 포함된 정점을 확인하는 방법
    정점의 길이만큼 Array<Boolean>을 선언
    최소신장트리에 포함된 정점이라면 해당 idx를 true로 설정

    #제출
    1. 틀렸습니다(7%)
    - poll한 node가 mst에 포함되어있는지 체크 

    2. 틀렸습니다(7%) 
    - 전체적으로 주석을 달며 코드 재작성

    3. 메모리초과(7%)
    - 2중 for문이 발생한 부분 해결

    4. 메모리초과(20%대)
    - edge가 너무 많이 queue에 추가되는것같음
    - mst와 포함되지 않은 정점과 연결된 edge만 queue에 넣음

    5. 성공

*/

import java.util.PriorityQueue
fun main(){
    Solution1197().solve()
}
class Solution1197 {
    data class Edge(
        val cost: Int,
        val node: Int
    )
    private lateinit var graph: Array<ArrayList<Edge>>
    private lateinit var mst: Array<Boolean>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (v, e) = br.readLine().split(' ').map{ it.toInt() }
        graph = Array(v+1){ArrayList()}
        mst = Array(v+1){false}
        repeat(e) { _ ->
            br.readLine().split(' ').map{ it.toInt() }.let {
                graph[it[0]].add(Edge(it[2], it[1]))
                graph[it[1]].add(Edge(it[2], it[0]))
            }
        }

        val minHeap = PriorityQueue<Edge>(compareBy({it.cost}))
        mst[1] = true // 첫번째 정점을 mst에 추가
        for(edge in graph[1]){ // 해당 정점과 연결된 edge들을 queue에 넣음
            minHeap.offer(edge)
        }
        var edgeCount = 0
        var cost = 0
        while(edgeCount < v-1){
            val cur = minHeap.poll()
            if(mst[cur.node]){ // edge와 연결된 정점이 mst에 포함되어있다면 넘어감
                continue
            }
            println("poll $cur, mst[${mst.toList()}]")
            edgeCount++
            cost += cur.cost // 간선을 mst에 넣음
            mst[cur.node] = true // 간선과 연결된 정점을 mst에 넣음
            for(nxt in graph[cur.node]) { // 방금 넣은 정점과 연결된 간선들을 queue에 넣음
                println("nxt[$nxt], mst[${mst[nxt.node]}]")
                if(!mst[nxt.node]) // 불필요한 간선들이 queue에 추가되지 않게 mst 검사
                    minHeap.offer(nxt)
            }
        }
        bw.write("$cost\n")
    
        bw.flush()
        bw.close()
        br.close()
    }
}