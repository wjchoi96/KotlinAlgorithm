//gold3
/*
    네이버 카페 문제50선 중 bfs문제

    다리만들기

    항상 두개 이상의 섬이 제공된다
    ==============================================
    1. 각 섬의 테두리를 구한다
    2. 각 섬의 테두리에서 출발해서 다른 섬의 테두리까지 거리를 구한다(bfs)
    3. board 를 순회하며 2번을 반복
    => 시간복잡도 감당이 안될거같은데


    ==============================================
    1. 각 섬들을 구분되게 찾아낸다
    - 모든 육지는1인데, 육지의 값을 변경해서 섬 구분?
    2. 그다음 최단거리를 구해준다
    -> board 를 순회하며 섬에서 출발
    -> 현재 섬이 아닌 섬을 만나면 value = min(value, current) 를 실시
    -> 바다를 만나면 offer

    근데 이러면 내가 처음에 생각한 벙법과 비슷한데
    => 내가 놓친점은 각 섬을 구분하는 방법

    ==============================================
    제출
    1. 틀렸습니다(21%)
    => make land 에서 start node 의 landValue 를 적용시키지 않았다

    2. 맞았습니다


    ==============================================
    나에게 부족한점
    1. 시간복잡도 계산을 못한다
    => 문제를 보고 이게 어느정도까지 가능한지 범위를 못잡네
*/

import java.io.*
import java.util.StringTokenizer
import java.util.Queue
import java.util.LinkedList
private lateinit var board : Array<Array<Int>>
private lateinit var disit : Array<Array<Int>>
private var landValue : Int = 2
private const val sea = 0
private val dx = arrayOf(1, 0, -1, 0)
private val dy = arrayOf(0, 1, 0, -1)
private var bridgeMin : Int = Int.MAX_VALUE
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()
    board = Array(n){Array(n){-1}}
    disit = Array(n){Array(n){-1}}
    for(i in 0 until n){
        var st = StringTokenizer(br.readLine())
        board[i] = Array(n){st.nextToken().toInt()}
    }

    for(x in 0 until n){
        for(y in 0 until n){
            // 바다가 아닌 섬, 아직 방문하지 않은 곳
            if(board[x][y] != sea && disit[x][y] == -1){
                makeLandBfs(Pair(x, y), n)
            }
        }
    }
    printBoard(n)

    for(x in 0 until n){
        for(y in 0 until n){
            if(board[x][y] != sea){
                makeBridgeBfs(Pair(x, y), n)
            }
        }
    }
    bw.write("$bridgeMin\n")
   
    bw.flush()
    bw.close()
    br.close()
}

private fun makeBridgeBfs(start : Pair<Int, Int>, n : Int){
    val currentLand = board[start.first][start.second]
    disit = Array(n){Array(n){-1}}
    val queue : Queue<Pair<Int, Int>> = LinkedList()
    queue.offer(start)
    disit[start.first][start.second] = 0

    while(!queue.isEmpty()){
        val node = queue.poll()
        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]

            // board 범위를 나가면 continue
            if(nx < 0 || nx >= n || ny < 0 || ny >= n){
                continue
            }
            // 같은 섬을 만나면 continue
            if(board[nx][ny] == currentLand){
                continue
            }
            // 방문한 곳이라면 continue
            if(disit[nx][ny] != -1){
                continue
            }
            // 다른 섬을 만나면 contiune, 최소거리 갱신
            if(board[nx][ny] >= 2){
                bridgeMin = Math.min(bridgeMin, disit[node.first][node.second])
                print("bridge[${disit[node.first][node.second]}] :")
                printNode(nx, ny)
                continue
            }

            queue.offer(Pair(nx, ny))
            disit[nx][ny] = disit[node.first][node.second] + 1
        }
    }

}

private fun makeLandBfs(start : Pair<Int, Int>, n : Int){
    val queue : Queue<Pair<Int, Int>> = LinkedList()
    queue.offer(start)
    disit[start.first][start.second] = 0
    board[start.first][start.second] = landValue

    while(!queue.isEmpty()){
        val node = queue.poll()
        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]
            if(nx < 0 || nx >=n || ny < 0 || ny >= n){
                continue
            }
            // 바다, 이미 방문한 섬이 체크
            if(board[nx][ny] == sea || board[nx][ny] > 1){
                continue
            }

            queue.offer(Pair(nx, ny))
            disit[nx][ny] = disit[node.first][node.second] + 1
            board[nx][ny] = landValue
        }
    }
    // 하나의 섬 탐색이 끝났다면 landValue를 높여서 다음 섬의 방문을 대비한다
    landValue++
}


private fun printNode(node : Pair<Int, Int>){
    printNode(node.first, node.second)
}
private fun printNode(x : Int, y : Int){
    print("node : [${x}, ${y}]\n")
}

private fun printBoard(n : Int){
    print("board print\n")
    for(x in 0 until n){
        for(y in 0 until n){
            print("${board[x][y]} ")
        }
        print("\n")
    }
        print("\n")
}