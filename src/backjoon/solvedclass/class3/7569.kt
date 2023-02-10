/*
    백준 7569
    solved.ac class 3 문제
    https://www.acmicpc.net/problem/7569
    gold 5

    토마토는 잘 익은것과, 익지 않은것이 존재
    하루가 지나면, 익은 토마토들의 인접한 익지 않은 토마토들이 익게됨
    - 인접한 곳이란 위, 아래, 왼쪽, 오른쪽, 앞, 뒤 6방향을 의미
    - 대각선 방향은 인접한것이 아님

    토마토가 혼자 저절로 익는 경우는 없음

    창고의 토마토가 며칠이 지나면 다 익을지 계산
    - 처음부터 모든 토마토가 익어있다면 0을 출력
    - 토마토가 모두 익지 못한다면 -1을 출력

    2 ≤ N ≤ 100 => 상자의 세로 칸
    2 ≤ M ≤ 100 => 상자의 가로 칸
    1 ≤ H ≤ 100 => 상자의 높이

    1은 익은 토마토
    0은 익지 않은 토마토
    -1은 빈칸

    토마토가 하나 이상 있는 경우만 입력에 주어짐
*/
/*
    bfs
    flood fill

    1. 총 몇개의 집합이 있는지 파악
    2. 익은 토마토가 하나도 없는 집합이 있다면 -1을 출력
    3. 익은 토마토에서 bfs를 수행해 depth 배열을 채움
    - 토마토에서의 거리를 의미. 한 칸에 하루가 걸리니 
    depth의 최대값이 모든 토마토가 익는 최소 일자가 될것

    board를 순회하며 익은 토마토를 만나면 bfs 진행
    진행하며 depth 배열을 채움
    - 이때, visit가 false인 곳에 한하여 진행

    depth 배열
    - 초기값 -1
    - 익은 토마토를 만났을때 0
    - 익지 않은 토마토를 만났을때 ++
    - visit의 역할도 수행할 수 있음

    #제출
    1. 틀렸습니다(0%)
    - 3중 for문중 M을 N으로 쓴 오타
    - bfs중 nextNode의 범위 체크중 board[0][0]size를 board[1].size로 잘못 기입한 오타

    2. 틀렸습니다(1%)
    - 반례를 찾다가 풀이 방식에 대한 글을 확인
    - 익은 토마토를 만나면 bfs를 하는게 아닌, 익은 토마토를 모두 queue에 넣고 한번에 bfs를 진행
        - 일단 이건 무시
    - 반례를 통해 확인한 결과, 이미 depth가 입력된 경로보다 더 빨리 토마토가 익을 수 있는 경로가 존재 가능

    3. 시간초과(1%)
    - bfs도중 경로에 익은 토마토가 존재할 필요 없음.
    continue하고, 해당 익은 토마토를 출발점으로 bfs를 시작하는게 최소거리
    - 가장 바깥쪽의 토마토에서만 bfs를 진행한다면, 자체적으로 최소값이 될테니까

    4. 시간초과(1%)
    - 익은 토마토를 만나면 bfs를 하는게 아닌, 익은 토마토를 모두 queue에 넣고 한번에 bfs를 진행해야 하는 이유
    - day가 지날때마다, 창고의 모든 익은 토마토로부터 1 depth씩 나아가야 함
    - 그렇기때문에, 최소값 비교를 수행할 필요도 없음

    5. 성공
    !!중요한점은 모든 익은 토마토들로부터, 동시에 bfs를 진행한다는 느낌
    날짜가 지났을때, 한번에 갱신되는 느낌
*/

import java.util.Queue
import java.util.LinkedList
fun main(){
    Solution7569().solve()
}
class Solution7569 {
    private lateinit var board: Array<Array<Array<Int>>>
    private lateinit var depth: Array<Array<Array<Int>>>
    private val queue: Queue<Pair<Int, Pair<Int, Int>>> = LinkedList()
    private val dx = arrayOf(0, 1, 0, -1, 0, 0)
    private val dy = arrayOf(1, 0, -1, 0, 0, 0)
    private val dh = arrayOf(0, 0, 0, 0, 1, -1)
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (M, N, H) = br.readLine().split(' ').map{it.toInt()}
        board = Array(H){Array(N){Array(M){-1}}}
        depth = Array(H){Array(N){Array(M){-1}}}

        repeat(H) { height -> 
            repeat(N) { x ->
                board[height][x] = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
            }
        }

        for(h in 0 until H){
            for(x in 0 until N){
                for(y in 0 until M){
                    if(board[h][x][y] == 1) { 
                        queue.offer(h to (x to y))
                        depth[h][x][y] = 0
                    }
                }
            }
        }

        bfs()

        var result = 0
        loop@for(h in 0 until H){
            for(x in 0 until N){
                for(y in 0 until M){
                    if(board[h][x][y] == 0){
                        if(depth[h][x][y] == -1){
                            result = -1
                            break@loop
                        }
                        result = Math.max(result, depth[h][x][y])
                    }
                }
            }
        }

        bw.write("$result\n")
    
        bw.flush()
        bw.close()
        br.close()
    }

    private fun bfs() {
        while(queue.isNotEmpty()){
            val node = queue.poll()
            var prevDepth = depth[node.first][node.second.first][node.second.second]

            for(i in 0 until dx.size){
                val nH = node.first + dh[i]
                val nX = node.second.first + dx[i]
                val nY = node.second.second + dy[i]

                if(nH !in 0 until board.size ||
                nX !in 0 until board[0].size ||
                nY !in 0 until board[0][0].size ) { // 범위 체크
                    continue
                }

                if(board[nH][nX][nY] == -1 || board[nH][nX][nY] == 1){ // 벽 or 익은 토마토는 건너뜀
                    continue
                }

                if(depth[nH][nX][nY] > -1) { // 이미 방문한 토마토(이미 익은 토마토)는 건너뜀
                    continue
                }
                
                queue.offer(nH to (nX to nY))
                depth[nH][nX][nY] = prevDepth + 1
            }
        }
    }
}

/*
반례
https://www.acmicpc.net/board/view/64283

2 2 2
0 -1
-1 -1
0 -1
0 1
answer:3

2 2 2
-1 -1
0 0
1 1
-1 -1

출력값
1

기대값
-1


5 3 2
0 0 0 -1 0
0 0 0 -1 -1
0 0 0 0 0
0 0 0 0 -1
0 0 1 0 0
0 0 0 0 0
=> -1


3 3 1
0 0 0
0 0 1
1 0 0
출력 : 3
정답 : 2


4 5 2
0 1 0 0
0 1 -1 1
-1 -1 1 -1
0 0 0 0
-1 -1 0 -1
-1 1 0 -1
0 0 -1 1
-1 0 1 0
0 0 0 -1
0 0 1 -1
출력 : 5
정답 : 3


5 5 2
1 -1 1 -1 1
0 0 -1 -1 1
0 -1 -1 1 0
0 -1 0 0 1
0 0 1 -1 1
1 -1 -1 -1 -1
0 -1 0 1 1
0 1 0 0 -1
-1 -1 -1 -1 -1
-1 0 0 1 -1
출력 : 4
정답 : 3


*/