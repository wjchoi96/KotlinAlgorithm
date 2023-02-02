/*
    백준 1504
    네이버 카페 문제 50선 중 다익스트라 유형 문제
    https://www.acmicpc.net/problem/1504

    gold 4

    방향성이 없는 그래프
    1번 정점에서 N번 정점으로 최단거리 이동
    - 임의로 주어진 두 정점은 반드시 통과
    - 한번 이동했던 정점, 간선도 다시 이동 가능
    - 반드시 최단경로로 이동

    2 ≤ N ≤ 800, 0 ≤ E ≤ 200,000 => N 정점의 개수, E 간선의 개수
    1 ≤ c ≤ 1,000 => c: 가중치

    임의의 정점 u와 v사이에는 간선이 최대 1개 존재

    1에서 N으로 가는 경로중에, 임의의 두 정점 u, v를 거치면서 최단 경로의 길이를 출력
    경로가 없다면 -1을 출력
*/
/*
    두 정점을 지나는 최단경로?
    두 정점을 지나는 경로 중 최단경로?

    1에서 u
    u에서 v
    v에서 N
    3번 돌려야 하려나

    #제출
    1. 틀렸습니다(3%)
    - 경로탐색을 하지 않고, 구간별 최소경로 가중치만 table을 통해 탐색하도록 수정

    2. 틀렸습니다(25%)
    - u, v중 최소값 최대값을 나눈다면 내가 임의로 
    start -> u -> v -> end 라는 순서를 지정하게 된것
    - start -> v -> u -> end가 최단경로일 수 있는데, 그 경우가 무시됨
        - 경로를 2개 계산하고, 더 작은값 출력

    3. 틀렸습니다(75%)
    - 경로가없으면 -1을 출력하는것 누락

    4. 정답
    - 힘들었다

*/

import java.util.PriorityQueue
fun main(){
    Solution1504().solve()
}
class Solution1504 {
    private lateinit var graph: Array<ArrayList<Pair<Int, Int>>>
    private val INF = 100000000
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (n, m) = br.readLine().split(' ').map{ it.toInt() }
        graph = Array(n+1){ArrayList()}

        repeat(m) {_ -> 
            br.readLine().split(' ').map{it.toInt()}.let {
                graph[it[0]].add(it[1] to it[2])
                graph[it[1]].add(it[0] to it[2])
            }
        }
        val (u, v) = br.readLine().split(' ').map{it.toInt()}
        var sum = dijkstra(1, u, n)
        var sum2 = dijkstra(1, v, n)

        sum += dijkstra(u, v, n)
        sum2 += dijkstra(v, u, n)

        sum += dijkstra(v, n, n)
        sum2 += dijkstra(u, n, n)

        bw.write("${Math.min(sum, sum2).let{if(it>=INF) "-1" else it}}\n")
    
        bw.flush()
        bw.close()
        br.close()
    }

    private fun dijkstra(start: Int, end: Int, n: Int): Int {
        val minHeap = PriorityQueue<Pair<Int, Int>>(compareBy({it.second}))
        val buffer = Array(n+1){INF}
        buffer[start] = 0
        val pathBuffer = Array(n+1){0}
        minHeap.offer(start to 0)
        while(!minHeap.isEmpty()) {
            val cur = minHeap.poll()
            if(buffer[cur.first] != cur.second) continue
            if(cur.first == end) continue
            println("$cur")
            println("q => ${minHeap.toList()}")
            for(nxt in graph[cur.first]) {
                (buffer[cur.first] + nxt.second).let { nxtCost ->
                    if(nxtCost < buffer[nxt.first]) {
                        buffer[nxt.first] = nxtCost
                        pathBuffer[nxt.first] = cur.first
                        minHeap.offer(nxt.first to nxtCost)
                    }
                }
            }
        }

        println("${buffer.toList()}")
        println("${pathBuffer.toList()}")

        val sb = StringBuilder()
        var sum = 0
        var cur = end
        while(cur != 0){
            sb.insert(0, "$cur ")
            sum += buffer[cur]
            cur = pathBuffer[cur]
        }       
        println("path[$start - $end] sum => ${buffer[end]}")
        println("path[$start - $end] => $sb")
        return buffer[end]
    }
}
/*
#반례
4 5
1 2 100
1 3 1
2 3 1
2 4 10
3 4 1
1 2
=> 4

4 5
1 2 100
1 3 1
2 3 1
2 4 10
3 4 1
1 4
=> 2

4 6
1 2 3
2 3 3
3 4 1
1 3 5
2 4 5
1 4 4
1 4
=> 4

4 5
1 2 3
1 3 1
1 4 1
2 3 3
3 4 4
2 3
=> 8




*/