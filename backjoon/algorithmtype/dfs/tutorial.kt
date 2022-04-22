// dfs 유형
/*
    dfs
    깊이 우선 탐색

    // java code : https://github.com/ndb796/python-for-coding-test/blob/master/5/8.java
    // 동빈나 유튜브 : https://www.youtube.com/watch?v=7C9RgOcvkvo&list=PLRx0vPvlEmdAghTr5mXQxGpHjWqSz0dgC&index=3
    // 바캉독 강의 : https://blog.encrypted.gg/942?category=773649

    0. 스택에 start 노드를 push
    
    1. 스택에서 노드를 pop한 후 해당 노드의 인접한 노드를 확인
    2. 아직 방문하지 않은 노드라면 스택에 push, 방문정보 갱신
    3. 스택이 빌때까지 1,2 반복 

    웬만한 dfs, bfs 코드는 x가 행, y가 열을 의미한다
    일반적인 좌표와는 반대이니, 숙지하고 익숙해질것

    bfs vs dfs

    1. bfs에서의 현재칸에서의 인접한칸은 거리가 1만큼 떨어져있다의 개념은 dfs에서 성립하지 않는다
    => 거리계산은 dfs 불가능

    다차원 배열에서의 순회는 bfs가 좀더 유능
    dfs는 그래프, 트리에서 주로 사용된다

    dfs : 재귀호출, 백트래킹, 조건 많은 그래프
    bfs : 큐, 최단거리, 최소비용, 모든 간선의 비용 1

    dfs 는 스택을 이용한방법, 재귀함수를 이용한 방법으로 구현 가능
    bfs 는 큐를 이용한 방법, 인접행렬, 인접리스트를 이용한방법(? 알아보자) 구현 가능

    =========================================================================

    네이버 카페 문제50선 중 dfs 유형 // https://cafe.naver.com/startdevelopercareer
    1. 백준 2667(solve)
    2. 백준 2468(solve)
    3. 백준 10026(solve)
    4. 백준 1987


*/

import java.util.Stack
fun main(args : Array<String>){
    print("dfs tutorial\n")
    print("dfs1\n")
    dfs1()
    print("dfs2\n")

    dfs2(Pair(0,0))
}



/*
    바킹독님 코드
*/

private val board : Array<Array<Int>> = arrayOf(
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
private val dx = arrayOf(0, 1, 0, -1)
private val dy = arrayOf(1, 0, -1, 0)
private fun dfs1(){
    val stack = Stack<Pair<Int, Int>>()
    val visit : Array<Array<Boolean>> = Array(board.size){Array(board[0].size) { false }}

    // 상하좌우 4방향을 의미
    // val dx = arrayOf(1, 0, -1, 0)
    // val dy = arrayOf(0, 1, 0, -1)

    stack.push(Pair(0, 0))
    visit[0][0] = true

    while(!stack.isEmpty()){
        val node = stack.pop()
        print("node[${node.first}][${node.second}]\n")
        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]

            if(nx<0 || nx>=board.size || ny<0 || ny>=board[0].size){
                continue
            }

            if(board[nx][ny] != 1 || visit[nx][ny] == true){
                continue
            }

            stack.push(Pair(nx, ny))
            visit[nx][ny] = true
        }
    }
}

// dfs 는 스택을 사용하는 특성상 재귀함수로도 구현이 가능하다
private val visit2 = Array(board.size){Array(board.size){false}}
// 보다시피 dest 와 dx, dy 를 반대로 둬봤는데, 탐색루트가 동일하다
private val dest = arrayOf(Pair(1,0), Pair(0,1), Pair(-1,0), Pair(0,-1))
// private val dx = arrayOf(0, 1, 0, -1)
// private val dy = arrayOf(1, 0, -1, 0)
private fun dfs2(start : Pair<Int, Int>){
    visit2[start.first][start.second] = true
    print("node[${start.first}][${start.second}]\n")
    for(i in 0 until 4){
        val nx = start.first + dest[i].first
        val ny = start.second + dest[i].second
        if(nx<0 || nx>=board.size || ny<0 || ny>=board[0].size){
            continue
        }
        if(visit2[nx][ny] == true || board[nx][ny] != 1){
            continue
        }
        dfs2(Pair(nx, ny)) 
    }
}
/*
    재귀 방식인 일반 stack 방식과 돌리는 순서가 약간 다른다

    스택은 for문 안에서 나중에 넣어진 node 를 먼저 pop하지만
    재귀는 재귀호출이 되면 바로 깊이깊이 이어져버리기때문에
    결과는 같지만, 순서는 다르다(x, y축 우선을 말하는것)
*/

/*
    00

    10
    01

    20
    01
*/