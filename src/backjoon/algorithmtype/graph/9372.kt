/*
    백준 그래프 이론/그래프 탐색 유형 문제
    sliver 4

    상근이의 여행
    https://www.acmicpc.net/problem/9372

    N개국을 여행하려 한다
    최대한 적은 종류의 비행기를 타고 국가를 이동하려 한다
    비행 스케쥴이 주어졌을 떄, 가장 적은 종류의 비행기를 타고 모든 국가들을 여행할 수 있도록 도와주자
    한 국가에서 다른 국가로 이동할 때, 다른 국가를 거쳐 가도(이미 방문한 국가라도) 된다

    국가의 수 N (정점)
    2<= N <= 1000
    비행기의 종류 M (간선)
    1<= M <= 10000

    a와 b를 왕복하는 비행기가 존재

    비행 스케쥴은 항상 연결 그래프를 이룬다
    => 임의의 두 정점 사이에 항상 경로가 존재
*/
/*
    가장 적은 비행기의 종류란?
    최단거리
*/
/*
    제출
    1. 성공
*/
/*
    개선
    문제의 의도를 잘 파악한건지 의문이 들어 검색 시도
    
    문제에서 간선에 가중치는 존재하지 않고, 모든 노드를 방문할 수 있는 최소 엣지 방문 횟수를 답으로 원하고 있습니다. 

    이 문제는 복잡하게 생각할 것 없이 전제에 모든 노드를 방문할 수 있는 경로가 존재한다는 것을 생각하면 됩니다. 
    이런 경우에는 간선이 N-1 개인 경우가 항상 최소입니다. (MST는 항상 간선의 개수가 N-1개입니다)
    답은 N-1만 출력해주면 됩니다. 
    => https://melonicedlatte.com/algorithm/2018/08/31/120730.html
    라고 한다..
*/

import java.util.Queue
import java.util.LinkedList
fun main(args: Array<String>){
    Solution9372().solve()
}
class Solution9372{
    private lateinit var graph: Array<ArrayList<Int>>
    private lateinit var visit: Array<Boolean>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        repeat(br.readLine().toInt()){
            val (n, m) = br.readLine().split(' ').map{it.toInt()}
            graph = Array(n+1){ArrayList()}
            repeat(m){
                br.readLine().split(' ').map{it.toInt()}.let {
                    graph[it[0]].add(it[1])
                    graph[it[1]].add(it[0])
                }
            }
            // var minCount = Int.MAX_VALUE
            // for(i in 1 until graph.size){
            //     visit = Array(n+1){false}
            //     val count = bfs(i)
            //     println("[$i] => $count")
            //     minCount = Math.min(minCount, count)
            // }
            visit = Array(n+1){false}
            bw.write("${bfs(1)-1}\n")
        }
    
        bw.flush()
        bw.close()
        br.close()
    }
    private fun bfs(start: Int): Int{
        var count = 1
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
                count++
            }
        }

        return count
    }
}
/*
1
3 3
1 2
2 3
1 3
====
2

1
5 4
2 1
2 3
4 3
4 5
====
4

*/