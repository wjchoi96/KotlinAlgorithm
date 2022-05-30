// gold5
/*
    바캉독님 bfs 강의 도중 나온 연습문제
    해당 문제를 보고 bfs라고 인지 가능한지 확인

    보관한지 하루가 지나면 익은 토마토들의 인접한 곳에 있는
    익지 않은 토마토가 익는다

    인접한곳은 왼쪽, 오른쪽, 위, 아래를 의미하며 대각선은 포함하지 않는다 => bfs, dfs 문제구나 싶긴한데
    
    몇일이 지나면 모든 토마토가 익는지 최소 일수를 구하라

    1 : 익은 토마토
    0 : 덜익은 토마토
    -1 : 빈칸

    몇일이 지나도 토마토가 다 익지 못한다면 -1 출력

    bfs를 한번 진행할때마다 board의 정보를 갱신해준다
    loop한번마다 하루가 지나는것

    !! 절대 방문 못하는 -1 node 가 있다면 n*m 의 방식으로 계산하는것은 불가능하다
    !! 전체에서 -1의 개수를 빼고, 0+1 의 개수를 counting 해서 정할까?
    !! 계산을 못하는 경우는 어떻게 찾아낼까
    => 하루가 지나는 연산을 진행하는데 pending list 가 비어있고, 완료조건을 충족하지않았다면 완료할수 없는 상자

    제출
    1. 시간초과
    : visit를 day마다 초기화시켜서 매번 처음부터 끝까지 bfs를 수행하는점을 개선
    2. 시간초과
    : day마다 맵 전체를 탐색하는것을 개선하기위해 pending tomato item을 start node로 bfs 수행하도록 수정
    : 전날에 익어있는 토마토들은 탐색할 필요가 없기 때문
    3. 틀렸습니다
    : pending tomato도 순회코드와, board전체 순회코드가 둘다 존재하는것을 확인, day++ 코드가 누락된것을 확인
    4. 성공

    개선안
    1. 익은 토마토를 queue에 넣고 bfs를 시작하면 시작점을 탐색할 필요 없이 알아서 돌아간다
    2. day => 미로찾기에서의 disit와 같다 => 시작node부터의 거리

    개선
    1. 틀렸습니다(17%)
    : 첫날에 익은 토마토가 여러개인데, 순서대로 방문처리가 되어서 disit가 1이 아니게 된다
    => bfs노드 방문 조건에서 익은 토마토는 건너뛰게 하도록 설정
    반례
    3 3
    1 1 1
    1 1 1
    1 1 0
    2. 성공
   
*/
import java.util.StringTokenizer
import java.util.LinkedList
import java.util.Queue
import java.io.*
private lateinit var board : Array<Array<Int>>
private lateinit var disit : Array<Array<Int>>
private var debugDay = -1
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val m = st.nextToken().toInt()
    val n = st.nextToken().toInt()

    board = Array(n) {Array(m){-1}}
    disit = Array(n){Array(m){0}}
    val queue : Queue<Pair<Int, Int>> = LinkedList()

    for(i in 0 until n){
        st = StringTokenizer(br.readLine())
        // board[i] = Array(m){st.nextToken().toInt()}
        for(j in 0 until m){
            board[i][j] = st.nextToken().toInt()
            when(board[i][j]){
                0 -> disit[i][j] = -1
                1 -> queue.offer(Pair(i, j))
            }
        }
    }


    val dx = arrayOf(1, 0, -1, 0)
    val dy = arrayOf(0, 1, 0, -1)
    while(!queue.isEmpty()){
        val node = queue.poll()

        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]
            if(nx < 0 || nx >= n || ny < 0 || ny >= m){
                continue
            }
            // 빈칸이거나, 이미 방문한 토마토인경우 
            // 방문한 토마토는 disit가 최소0
            if(board[nx][ny] == -1 || disit[nx][ny] >= 0){
                continue
            }

            disit[nx][ny] = disit[node.first][node.second]+1
            queue.offer(Pair(nx, ny))
            debugDay = Math.max(debugDay, disit[nx][ny]) // for dubug
            printNode(queue.last())
        }
    }

    var day = -1
    for(i in 0 until n){
        for(j in 0 until m){
            if(disit[i][j] == -1){
                bw.write("-1\n")
                bw.flush()
                bw.close()
                br.close()
                return
            }
            day = Math.max(day, disit[i][j])
        }
    }

    bw.write("$day\n")
   
    bw.flush()
    bw.close()
    br.close()

}

private fun printBoard(){
    print("\n")
    for(i in 0 until board.size){
        for(j in 0 until board[i].size){
            print("${board[i][j]} ")
        }
        print("\n")
    }
}

private fun printNode(node : Pair<Int, Int>){
    printNode(node.first, node.second)
}
private fun printNode(nx : Int, ny : Int){
    print("day[$debugDay] : node[$nx, $ny]\n")
}
