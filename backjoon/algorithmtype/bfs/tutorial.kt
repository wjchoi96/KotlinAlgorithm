/*
    bfs
    너비 우선 탐색

    // java code : https://github.com/ndb796/python-for-coding-test/blob/master/5/8.java
    // 동빈나 유튜브 : https://www.youtube.com/watch?v=7C9RgOcvkvo&list=PLRx0vPvlEmdAghTr5mXQxGpHjWqSz0dgC&index=3
    // 바캉독 강의 : https://blog.encrypted.gg/941?category=773649

    0. 첫번째 노드를 queue에 저장, 방문 정보 저장

    1. queue애서 방문한 노드를 pop
    2. pop된 노드에서 인접한 방문하지 않은 노드들을 queue에 저장(여러개라면 노드가 적은것부터)
    
    1,2 반복

    웬만한 dfs, bfs 코드는 x가 행, y가 열을 의미한다
    일반적인 좌표와는 반대이니, 숙지하고 익숙해질것
*/

import java.util.*
fun main(args : Array<String>){
    print("bfs tutorial\n")
    bfs1()
}

// 바캉독 코드
/*
    자주 실수하는 것들
    1. 시작점 방문 이력 누락
    2. queue에 저장할때 방문이력을 갱신하는 대신 queue에서 빼낼때 방문이력 갱신
    3. 이웃한 원소가 범위를 벗어났는지에 대한 체크를 잘못하 

    연습문제
    1. 백준 1926번(solve)
    2. 백준 2178번(solve)
    3. 백준 7576번(solve)

    4. 백준 7579번 - 나중에 풀어볼것
    => https://www.acmicpc.net/problem/7569
    => 3차원 토마토 문제인데, 다른문제 먼저 풀고 시도해보자
    
    5. 백준 4179번(solve)

    6. 백준 18809번 - 나중에 풀어볼것 
    => https://www.acmicpc.net/problem/18809
    => 백트래킹이 추가된 문제
    => 두종류의 bfs 혹은 dfs가 진행되면서 서로에게 여향을 준다
    
    7. 백준 1697번(solve)


    네이버 카페 문제50선 중 bfs문제 // https://cafe.naver.com/startdevelopercareer/6?boardType=L
    1. 백준 2667(solve)




*/ 
private fun bfs1(){
    val board : Array<Array<Int>> = arrayOf(
        arrayOf(1,1,1,0,1,0,0,0,0,0),
        arrayOf(1,0,0,0,1,0,0,0,0,0),
        arrayOf(1,1,1,0,1,0,0,0,0,0),
        arrayOf(1,1,0,0,1,0,0,0,0,0),
        arrayOf(0,1,0,0,0,0,0,0,0,0),
        arrayOf(0,0,0,0,0,0,0,0,0,0),
        arrayOf(0,0,0,0,0,0,0,0,0,0),
        arrayOf(0,1,0,0,0,0,0,0,0,0),
        arrayOf(0,0,0,0,0,0,0,0,0,0),
        arrayOf(0,0,0,0,0,0,0,0,0,0)
    ) 
    // 상하좌우 4방향을 의미
    val dx = arrayOf(1, 0, -1, 0)
    val dy = arrayOf(0, 1, 0, -1)

    val visit : Array<Array<Boolean>> = Array(10) {Array(10){false}} // 방문 이력
    val queue : Queue<Pair<Int, Int>> = LinkedList()

    // 0,0 노드 방문
    queue.offer(Pair(0,0))
    visit[0][0] = true

    while(!queue.isEmpty()){
        val cur = queue.poll()
        print("[${cur.first}, ${cur.second}]\n")
        
        // 상하좌우 칸을 살펴본다 
        // [0,0] 기준으로 [1,0], [0,1], [-1,0], [0,-1] 칸 순으로 조회
        for(i in 0 until 4){
            val nx = cur.first + dx[i]
            val ny = cur.second + dy[i]

            // 범위를 벗어난 좌표라면 건너뛴다
            if(nx < 0 || nx >= 10 || ny < 0 || ny >= 10){
                continue
            }

            // 이미 방문한 칸이거나 방문할 칸(파랑칸)이 아닌경우
            if(visit[nx][ny] == true || board[nx][ny] == 0){
                continue
            }

            visit[nx][ny] = true // 방문 이력 저장
            queue.offer(Pair(nx, ny)) // queue에 저장
        }
    }
}
