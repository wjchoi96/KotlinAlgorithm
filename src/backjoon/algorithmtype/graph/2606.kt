/*
    백준 그래프 이론/그래프 탐색 유형 문제
    solved.ac class 3 문제
    sliver 3

    바이러스

    신종 바이러스인 웜 바이러스는 네트워크를 통해 전파된다
    한 컴퓨터가 웜 바이러스에 걸리면 그 컴퓨터와 네트워크 상에서 연결되어 있는 모든 컴퓨터는 웜 바이러스에 걸린다

    1번 컴퓨터가 웜 바이러스에 걸렸다
    1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 출력
*/
/*
    1초
    컴퓨터의 수는 100개 이하
*/
/*
    컴퓨터의 수가 적어 인접행렬로 풀이가 가능하지만, 인접 리스트로 진행해 볼 예정
*/
/*
    제출
    1. 성공
*/

import java.util.Queue
import java.util.LinkedList
fun main(args: Array<String>){
    Solution2606().solve()
}
class Solution2606 {
    private lateinit var graph: Array<ArrayList<Int>>
    private lateinit var visit: Array<Boolean>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        graph = Array(n+1){ArrayList()}
        visit = Array(n+1){false}
        repeat(br.readLine().toInt()){
            br.readLine().split(' ').map{it.toInt()}.let {
                graph[it[0]].add(it[1])
                graph[it[1]].add(it[0])
            }
        }
        bw.write("${bfs(1)}\n")
    
        bw.flush()
        bw.close()
        br.close()
    }
    private fun bfs(start: Int): Int{
        var count = 0
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