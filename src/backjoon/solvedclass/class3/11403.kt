/*
    백준 11403
    solved.ac class 3 문제
    https://www.acmicpc.net/problem/11403

    silver 1

    가중치 없는 방향 그래프 G
    모든 정점 (i, j)에 대해서, i에서 j로 가는 경로가 있는지 없는지 구하시오

    1 ≤ N ≤ 100 => 정점의 개수
*/
/*
    인접행렬로 구성
    i에서 bfs를 진행해 j로 가는 경로가 있는지 조사

    bfs로 전구간 검색하려 했는데, 플로이드 알고리즘 유형으로 되어있음
    - 정점의 개수가 100개뿐이라 bfs로도 가능할 것 같아서 우선 진행
*/


import java.util.Queue
import java.util.LinkedList
import kotlin.time.seconds
fun main(){
    Solution11403().solve()
}
class Solution11403 {
    private lateinit var board: Array<Array<Int>>
    private lateinit var result: Array<Array<Int>>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        board = Array(n){Array(n){0}}
        result = Array(n){Array(n){0}}
        repeat(n){
            board[it] = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
        }
        
        

    
        bw.flush()
        bw.close()
        br.close()
    }
    private fun bfs(start: Pair<Int, Int>, size: Int) {
        val dx = arrayOf(0, 1, 0, -1)
        val dy = arrayOf(1, 0, -1, 0)
        val queue: Queue<Pair<Int, Int> = LinkedList()
        val visit = Array(size){Array(size){false}}
        queue.offer(start)
        visit[start.first][start.second] = true

        while(queue.isNotEmpty()){
            val node = queue.poll()
            for(i in 0 until 4){
                val nxt = node.first + dx[i] to node.second + dy[i]
                
            }
        }
    }
}