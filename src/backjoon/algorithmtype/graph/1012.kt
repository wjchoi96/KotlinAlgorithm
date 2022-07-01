/*
    백준 그래프 이론/그래프 탐색 유형 문제
    solved.ac class 3 문제
    sliver 2

    유기농 배추

    농약을 쓰지 않고 배추를 재배하려면, 배추를 해충으로부터 보호하는 배추흰지렁이를 구입했다
    이 지렁이는 배추근처에 서식하며 해충을 잡아 먹음으로써 배추를 보호한다
    특히, 어떤 배추에 배추흰지렁이가 한마리라도 살고 있다면, 이 지렁이는 인접한 다른 배추로 이동할 수 있어,
    그 배추들 역시 해출으로부터 보호받을 수 있다
    - 한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우 서로 인접해 있는 것

    배추를 재배하는 땅은 고르지 못해서, 배추를 군데군데 심어 놓았다
    배추들이 모여있는 곳에는 배추흰지렁이가 한마리만 있으면 되므로, 서로 인접해 있는 배추들이
    몇 군데이 퍼져있는지 조사하면 총 몇마리의 지렁이가 필요한지 알 수 있다
    0은 땅
    1은 배추

    1초
    테스트 케이스의 개수 T
    배추밭의 가로, 세로 M,N
    1<= N,M <= 50

    배추가 심어져있는 위치의 개수K
    1<= K <= 2500
*/
/*
    flood fit, 섬 개수 찾기 유형 문제
    가로 세로가 적어 인접행렬 방식으로 진행 가능

    입력 예제를 보니 0-based-index
*/
/*
    제출
    1. 정답
    - bfs

    2. 성공
    - dfs
    
*/

import java.util.Queue
import java.util.LinkedList
fun main(args: Array<String>){
    Solution1012().solve()
}
class Solution1012 {
    private lateinit var board: Array<Array<Int>>
    private lateinit var visit: Array<Array<Boolean>>
    private val dx = arrayOf(1, 0, -1, 0)
    private val dy = arrayOf(0, 1, 0, -1)
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        repeat(br.readLine().toInt()){
            val (m, n, k) = br.readLine().split(' ').map{it.toInt()}
            board = Array(n){Array(m){0}}
            visit = Array(n){Array(m){false}}
            repeat(k){
                br.readLine().split(' ').map{it.toInt()}.let {
                    board[it[1]][it[0]] = 1
                }
            }
            var count = 0
            for(x in 0 until n){
                for(y in 0 until m){
                    if(board[x][y] == 0) continue
                    if(visit[x][y]) continue
                    count++
                    dfs(x to y)
                }
            }
            bw.write("${count}\n")
        }
    
        bw.flush()
        bw.close()
        br.close()
    }
    private fun bfs(start: Pair<Int, Int>){
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        queue.offer(start)
        visit[start.first][start.second] = true
        while(!queue.isEmpty()){
            val pollNode = queue.poll()
            for(i in 0 until 4){
                val newNode = pollNode.first+dx[i] to pollNode.second+dy[i]
                if(!(newNode.first in 0 until board.size) || !(newNode.second in 0 until board[0].size)) continue
                if(board[newNode.first][newNode.second] == 0) continue
                if(visit[newNode.first][newNode.second]) continue
                visit[newNode.first][newNode.second] = true
                queue.offer(newNode)
            }
        }
    }
    private fun dfs(v: Pair<Int, Int>){
        visit[v.first][v.second] = true
        for(i in 0 until 4){
            val newNode = v.first+dx[i] to v.second+dy[i]
            if(!(newNode.first in 0 until board.size) || !(newNode.second in 0 until board[0].size)) continue
            if(board[newNode.first][newNode.second] == 0) continue
            if(visit[newNode.first][newNode.second]) continue
            dfs(newNode)
        }
    }
}