/*
    백준 1753
    바킹독님 알고리즘 강의 중 다익스트라 알고리즘 단원 연습문제
    https://www.acmicpc.net/problem/1753

    gold 4

    방향그래프가 주어지면, 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오
    간선의 가중치 10이하의 자연수

    정점의 개수 V와 간선의 개수 E
    1 ≤ V ≤ 20,000
    1 ≤ E ≤ 300,000
    1 ≤ K ≤ V => K는 시작 정점의 번호
    가중치 w는 10 이하의 자연수

    시작점 자신은 0
    경로가 존재하지 않는 경우 INF를 출력
*/
/*
    가중치가 10 이하이니 
    INF는 10*20000 해서 200000정도로 두면 될듯

    #제출
    1. 틀렸습니다(2%)
    - queue에 node를 offer해줄때, node.first to table[node.first] 값을 넣어줌
    - 최단거리 테이블에 갱신된 값을 가중치로 하여 간선을 queue에 추가

    2. 메모리초과(2%)
    - 최단거리가 갱신된 경우에만, node를 queue에 추가
    - 위상정렬때도 그랬지만, 넣을 필요없는 node를 queue에서 제거해줘서 메모리 초과를 해결

    3. 성공

*/

import java.util.PriorityQueue
fun main(){
    Solution1753().solve()
}
class Solution1753 {
    private lateinit var graph: Array<ArrayList<Pair<Int, Int>>>
    private lateinit var table: Array<Int>
    private val INF = 200000
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (v, e) = br.readLine().split(' ').map{it.toInt()}
        val start = br.readLine().toInt()
        graph = Array(v+1){ArrayList()}
        table = Array(v+1){INF}
        table[start] = 0

        repeat(e) { _ ->
            br.readLine().split(' ').map{it.toInt()}.let {
                graph[it[0]].add(it[1] to it[2])
            }
        }

        val minHeap = PriorityQueue<Pair<Int, Int>>(compareBy({it.second}))
        minHeap.offer(start to 0)

        while(!minHeap.isEmpty()) {
            val cur = minHeap.poll()
            if(table[cur.first] != cur.second) continue
            bw.write("$cur\n")

            for(node in graph[cur.first]) {
                if(table[cur.first] + node.second < table[node.first]) {
                    table[node.first] = table[cur.first] + node.second
                    minHeap.offer(node.first to table[node.first])
                }
            }
            bw.write("queue => ${minHeap.toList()}\n")
            bw.write("${table.toList()}\n")
        }
        for(i in 1..v){
            bw.write("${if(table[i]==INF) "INF" else table[i]}\n")
        }

    
        bw.flush()
        bw.close()
        br.close()
    }
}

/*
#반례
5 6
3
3 1 100
3 2 50
3 4 1
2 1 1
4 1 1
1 5 1

# ans
2
50
0
1
3
*/