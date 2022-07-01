/*
    바킹독님 알고리즘 강의 중 그래프 단원 연습문제
    sliver 2

    DFS와 BFS

    그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성
    => DFS 재귀, 비재귀 방식에 대해 방문 순서 차이를 고려해야할듯

    방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문,
    => 재귀방식 DFS 구현 필요
    더 이상 방문할 수 있는 점이 없는 경우 종료한다.
    정점 번호는 1번부터 N번까지이다

    2초

    정점의 개수 N
    1<= N <= 1000
    간선의 개수M
    1<= M <= 10000

    탐색을 시작할 정점의 번호 V가 주어진다
    두 정점 사이에 여러 개의 간선이 존재할 수 있고, 입력으로 주어지는 간선은 양방향이다
*/
/*
    제출
    1. 정답
*/

import java.util.Queue
import java.util.LinkedList
fun main(args: Array<String>){
    Solution1260().solve()
}
class Solution1260 {
    private lateinit var graph: Array<ArrayList<Int>>
    private lateinit var visit: Array<Boolean>
    fun solve(){
        val br = System.`in`.bufferedReader()

        val (n, m, v) = br.readLine().split(' ').map{it.toInt()}
        graph = Array(n+1){ArrayList()}
        repeat(m){
            br.readLine().split(' ').map{it.toInt()}.let {
                graph[it[0]].add(it[1])
                graph[it[1]].add(it[0])
            }
        }
        for(i in 1 until graph.size){
            graph[i].sort()
        }
        // do dfs
        visit = Array(n+1){false}
        dfs(v)
        println("")
        // do bfs
        visit = Array(n+1){false}
        bfs(v)
        println("")


        br.close()
    }

    private fun bfs(start: Int){
        val queue: Queue<Int> = LinkedList()
        queue.offer(start)
        visit[start] = true
        while(!queue.isEmpty()){
            val pollNode = queue.poll()
            print("$pollNode ")
            for(i in 0 until graph[pollNode].size){
                val newNode = graph[pollNode][i]
                if(visit[newNode]) continue
                visit[newNode] = true
                queue.offer(newNode)
            }
        }
    }
    private fun dfs(v: Int){
        print("$v ")
        visit[v] = true
        for(i in 0 until graph[v].size){
            if(visit[graph[v][i]]) continue
            dfs(graph[v][i])
        }
    }
}