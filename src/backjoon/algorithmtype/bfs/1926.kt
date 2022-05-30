// sliver 1
/*
    bfs tutorial 에서 넘어옴
    바캉독님 강의 도중 제시된 연습문제

    큰 도화지에 그림이 그러져있다.
    그림의 개수 + 그림 중 넓이가 가장 넓은것의 넓이 출력

    1로 연결된것을 하나의 그림으로 정의
    그림의 넓이란 그림에 포한된 1의 개수

    도화지의 세로 크기 n
    도화지의 가로 크기 m
    1<= n,m <= 500  

    제출이력 
    1. 53% 오답 => 그림이 없는경우 max값이 Int.MIN_VALUE 그대로 출력된다
    2. 성공
*/

import java.io.*
import java.util.StringTokenizer
import java.util.LinkedList
import java.util.Queue
private lateinit var board : Array<Array<Int>>
private lateinit var visit : Array<Array<Boolean>>
private var queue : Queue<Pair<Int, Int>> = LinkedList()
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    board = Array(n){Array(m){0}}
    visit = Array(n){Array(m){false}}
    for(i in 0 until n){
        st = StringTokenizer(br.readLine())
        board[i] = Array(m){st.nextToken().toInt()}
    }

    var max = 0 // 그림의 최대 넓이 => 그림의 넓이는 최소값이 0으로 고정되어있기때문에 0으로 초기화
    var drawalCount = 0 // 그림의 개수
    for(x in 0 until n){
        for(y in 0 until m){
            //방문 가능한 노드 중 방문하지 않은 노드를 발견했다면
            if(board[x][y] == 1 && visit[x][y] == false){
                val area = bfs(Pair(x,y), n, m) // 해당 노드를 시작으로 연결된 노드들을 너비우선탐색
                max = Math.max(area, max) // 최대 넓이 갱신
                drawalCount++
            }
        }
    }

    bw.write("$drawalCount\n$max\n")

    bw.flush()
    bw.close()
    br.close()
}

// 너비우선 탐색을 실시하고, 방문한 노드의 개수를 리턴
private fun bfs(start : Pair<Int, Int>, n : Int, m : Int) : Int{
    queue.offer(start)
    visit[start.first][start.second] = true
    printNode(start)
    var nodeCount = 1
    
    val dx = arrayOf(1, 0, -1, 0)
    val dy = arrayOf(0, 1, 0, -1)

    while(!queue.isEmpty()){
        val node = queue.poll()
        
        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]
            
            // 범위를 벗어난 경우
            if(nx < 0 || nx >= n || ny < 0 || ny >= m){
                continue
            }
            // 방문한 노드이거나, 방문할 노드가 아닌경우
            if(board[nx][ny] == 0 || visit[nx][ny] == true){
                continue
            }
            visit[nx][ny] = true // 방문 이력 갱신
            queue.offer(Pair(nx, ny)) // 방문
            nodeCount++
            printNode(nx, ny)
        }
    }
    print("\n")
    return nodeCount
}


private fun printNode(node : Pair<Int, Int>){
    printNode(node.first, node.second)
}
private fun printNode(x : Int, y : Int){
    print("node : [${x}, ${y}]\n")
}
