/*
    백준 14938
    solved.ac class 4 문제
    https://www.acmicpc.net/problem/14938

    양방향 그래프
    낙하한 지역을 중심으로, 거리가 수색범위 m이내의 모든 지역의 아이템 습득이 가능
    - 특정 정점의 일정 거리 이내에 있는 정점의 아이템을 모두 습득

    1 ≤ n ≤ 100 => 지역 개수
    1 ≤ r ≤ 100 => 간선 개수
    1 ≤ m ≤ 15 => 수색범위
    1 ≤ l ≤ 15 => 가중치
    1 ≤ t ≤ 30 => 각 구역의 Item 수
*/
/*
    특정 정점으로부터, 나머지 정점들까지의 거리가 필요
    근데, 시작정점이 정해져있지 않으므로, 모든 정점쌍 사이의 최단거리가 필요
    -> 플로이드 
    -> O(V^3)

    다익스트라를 V번 진행
    -> O(E log E) * O(V)
    -> O(VE log E)

    다익스트라가 더 빠름

    #다익스트라 제출
    1. 성공

*/

import java.util.PriorityQueue
fun main(){
    Solution14938().solve()
}
class Solution14938 {
    private lateinit var graph: Array<ArrayList<Pair<Int, Int>>>
    private lateinit var table: Array<Array<Int>>
    private lateinit var items: Array<Int>
    private val INF = 2000000000
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (n, m, r) = br.readLine().split(' ').map{ it.toInt() }
        graph = Array(n+1){ArrayList()}
        table = Array(n+1){Array(n+1){INF}}
        items = Array(n+1){0}
        br.readLine().split(' ').map{ it.toInt() }.forEachIndexed { i, v -> 
            items[i+1] = v
        }

        repeat(r) {
            br.readLine().split(' ').map{ it.toInt() }.let {
                graph[it[0]].add(it[1] to it[2])
                graph[it[1]].add(it[0] to it[2])
            }
        }

        val minHeap = PriorityQueue<Pair<Int, Int>>(compareBy({it.second}))
        for(start in 1..n) {
            minHeap.offer(start to 0)
            table[start][start] = 0

            while(!minHeap.isEmpty()) {
                val cur = minHeap.poll()
                if(table[start][cur.first] != cur.second) continue

                for(nxt in graph[cur.first]) {
                    (table[start][cur.first] + nxt.second).let {
                        if(it < table[start][nxt.first]) {
                            table[start][nxt.first] = it
                            minHeap.offer(nxt.first to it)
                        }
                    }
                }
            }
        }

        var max = 0
        bw.write("items => ${items.toList()}\n")
        for(start in 1..n) {
            val curTable = table[start]
            bw.write("start[$start] => ${curTable.toList()}\n")
            var count = 0
            for(i in 1..n) {
                if(curTable[i] <= m) count += items[i]
            }
            max = Math.max(max, count)
        }
        bw.write("${maxTable.toList()}\n")
        bw.write("$max\n")

    
        bw.flush()
        bw.close()
        br.close()
    }
}