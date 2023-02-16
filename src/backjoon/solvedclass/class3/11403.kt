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
    - 인접행렬이니, bfs의 시간복잡도가 O(VE) 
    - E는 최대 V와 같으니, O(V^2)
    - 모든 정점에 대해 bfs를 수행하니 O(V^3). 플로이드 시간복잡도와 같음
    - 코드는 플로이드가 한결 간결함
    - 익숙한건 bfs

    #bfs 제출
    1. 성공

    #플로이드 제출
    1. 성공
*/


import java.util.Queue
import java.util.LinkedList
fun main(){
    Solution11403().solveFloyd()
}
class Solution11403 {
    private lateinit var board: Array<Array<Int>>
    private lateinit var result: Array<Array<Int>>
    private val INF = 200000000
    
    fun solveFloyd() {
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        board = Array(n){Array(n){0}}
        repeat(n){
            board[it] = br.readLine().split(' ').map{it.toInt()}.map{
                if(it == 0)
                    INF
                else 
                    it
            }.toTypedArray()
        }

        for(i in 0 until n) {
            for(x in 0 until n) {
                for(y in 0 until n) {
                    board[x][y] = Math.min(board[x][i] + board[i][y], board[x][y])
                }
            }
        }

        for(x in 0 until n){
            for(y in 0 until n) {
                bw.write("${board[x][y].let{if(it==INF) 0 else 1}} ")
            }
            bw.write("\n")
        }

        bw.flush()
        bw.close()
        br.close()
    }

    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        board = Array(n){Array(n){0}}
        result = Array(n){Array(n){0}}
        repeat(n){
            board[it] = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
        }
        repeat(n) {
            bfs(it, n)
        }

        for(x in 0 until n){
            for(y in 0 until n) {
                bw.write("${result[x][y]} ")
            }
            bw.write("\n")
        }

    
        bw.flush()
        bw.close()
        br.close()
    }
    private fun bfs(start: Int, size: Int) {
        val queue: Queue<Int> = LinkedList()
        val visit = Array(size){false}
        queue.offer(start)
        // 1 -> 2 -> 3 -> 1로 되돌아 올 수 있는 경로가 존재하기에, 
        // visit[start] = true를 하지 않음

        while(queue.isNotEmpty()){
            val cur = queue.poll()
            for(nxtIdx in 0 until board[cur].size) {
                if(board[cur][nxtIdx] == 0) {
                    continue
                }
                if(visit[nxtIdx]) {
                    continue
                }
                result[start][nxtIdx] = 1
                visit[nxtIdx] = true
                queue.offer(nxtIdx)
            }
        }
    }
}