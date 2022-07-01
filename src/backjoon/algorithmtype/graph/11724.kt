/*
    바킹독님 알고리즘강의 중 그래프 단원 연습문제
    sliver 2

    연결 요소의 개수

    방향 없는 그래프가 주어졌을 때, 연결 요소(Connected Component)의 개수를 구하시오

    정점의 개수 N
    1<= N <= 1000
    간선의 개수 M
    0<= M <= N*(N-1)/2

    같은 간선은 한 번만 주어진다

    - 연결 요소란?
    => 이어지지 않은 두개의 그래프를 2개의 그래프라 표현할 수 있지만
    => 하나의 그래프에 두 개의 연결 요소를 가진다 할 수 있다

    => flood fit 문제 였던것
    => 섬 개수 찾기 문제 등
*/

/*
    제출
    1. 성공
    - bfs 구현

    2. 성공
    - dfs 구현
*/


import java.util.LinkedList
import java.util.Queue
fun main(args: Array<String>){
    Solution11724().solve()
}
class Solution11724 {
    private lateinit var graph: Array<ArrayList<Int>>
    private lateinit var visit: Array<Boolean>
    private var count = 0
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (n, m) = br.readLine().split(' ').map{it.toInt()}
        graph = Array(n+1){ArrayList()}
        repeat(m) {
            br.readLine().split(' ').map{it.toInt()}.let { 
                graph[it[0]].add(it[1])
                graph[it[1]].add(it[0])
            }
        }
        visit = Array(n+1){false}
        
        for(g in 1 until graph.size){
            if(visit[g]) continue
            count++
            dfs(g)
        }
        bw.write("$count\n")
        
    
        bw.flush()
        bw.close()
        br.close()
    }
    private fun bfs(start: Int){
        val queue: Queue<Int> = LinkedList()
        queue.offer(start)
        visit[start] = true
        while(!queue.isEmpty()){
            val pollNode = queue.poll()
            for(i in 0 until graph[pollNode].size){
                val newNode = graph[pollNode][i]
                if(visit[newNode]) continue
                queue.offer(newNode)
                visit[newNode] = true
            }
        }
    }
    private fun dfs(node: Int){
        visit[node] = true // start node visit 처리를 일관되게 해주기 위해 여기에서 visit 처리
        for(i in 0 until graph[node].size){
            if(visit[graph[node][i]]) continue
            dfs(graph[node][i])
        }
    }
}