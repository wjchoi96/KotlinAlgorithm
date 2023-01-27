/*
    백준 1368
    바킹독님 알고리즘 강의 중 최소신장트리 단원 연습문제
    https://www.acmicpc.net/problem/1368

    gold 2

    N개의 논에 물을 대려 함
    - 논에 직접 우물을 파는 것
    - 물을 대고있는 다른 논으로부터 물을 끌어오는법

    각각의 논에 대해 우물을 파는 비용과, 논들 사이에 물을 끌어오는 비용이 주어짐
    최소의 비용으로 모든 논에 물을 대는 문제

    모든 논에 물을 대는데 필요한 최소비용을 출력

    1 ≤ N ≤ 300 => 논의 수
    1 ≤ Wi ≤ 100,000 => i번째 논에 우물을 파는 비용
    1 ≤ Pi,j ≤ 100,000, Pi,j = Pj,i, Pi,i = 0 => i번째 논과 j번째 논을 연결하는 비용

*/
/*
    간선을 추가할때, i번째 논에서 i번째 논으로 물을 끌어오는것에 대한 간선도 추가해주면 될듯?

    1. 첫번째 정점을 mst에 추가
    - 자신의 우물을 파는비용이 최소인 우물을 선택
    2. mst에 추가된 정점과, 추가되지 않은 정점들을 연결하는 간선들을 Queue에 추가
    - 이때, 각 논에 물을 직접 대는 간선을 모두 체크
    - 반대정점이 mst에 추가되지 않은 간선에 한함
    - queue에 너무 많은 간선이 쌓이지 않게하기 위함
    3. 최소신장트리에 node가 n개 추가될때까지 진행

    #제출
    1. 성공
*/

import java.util.PriorityQueue
fun main(){
    Solution1368().solve()
}
class Solution1368 {
    data class Edge (
        val node: Int,
        val cost: Int
    )
    private lateinit var graph: Array<ArrayList<Edge>>
    private lateinit var mst: Array<Boolean>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        graph = Array(n+1){ArrayList()}
        mst = Array(n+1){false}

        val minHeap = PriorityQueue<Edge>(compareBy({it.cost}))
        repeat(n) {  
            minHeap.offer(Edge(it+1, br.readLine().toInt()))
        }

        repeat(n) { idx ->
            br.readLine().split(' ').map{it.toInt()}.forEachIndexed { i, v -> 
                if(v != 0) {
                    graph[idx+1].add(Edge(i+1, v))    
                }
            }
        }
        
        var nodeCount = 0
        var cost = 0

        while(nodeCount < n) {
            val curEdge = minHeap.poll()
            println("poll => ${curEdge}")
            if(mst[curEdge.node]) {
                continue
            }
            mst[curEdge.node] = true
            nodeCount++
            cost += curEdge.cost
            
            for(nxtEdge in graph[curEdge.node]) {
                if(!mst[nxtEdge.node]) {
                    minHeap.offer(nxtEdge)
                }
            }
        }

        bw.write("$cost\n")

    
        bw.flush()
        bw.close()
        br.close()
    }
}