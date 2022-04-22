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
    


*/

import java.util.Stack
fun main(args : Array<String>){
    print("bfs tutorial\n")
    dfs1()
}



/*
    바킹독님 코드
*/

private fun dfs1(){
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
    val stack = Stack<Pair<Int, Int>>()
    val visit : Array<Array<Boolean>> = Array(board.size){Array(board[0].size) { false }}

    // 상하좌우 4방향을 의미
    // val dx = arrayOf(1, 0, -1, 0)
    // val dy = arrayOf(0, 1, 0, -1)
    val dx = arrayOf(0, 1, 0, -1)
    val dy = arrayOf(1, 0, -1, 0)

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
