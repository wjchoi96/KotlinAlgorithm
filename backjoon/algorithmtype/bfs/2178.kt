//sliver 1
/*
    바캉독님 강의 도중 나온 연습문제
    미로찾기

    N*M 크기의 미로
    1 : 이동 가능한 칸
    0 : 이동 불가능한 칸
    (1,1) 에서 출발하여 (N,M)위치로 이동할때 지나는 최소 칸 수
    (1,1) 이 (0,0)을 의미하는것 같다

    bfs 를 진행하면서 각 노드까지의 방문거리를 저장
    [0][0] 부터의 [x][y]까지의 거리 정보
    거리정보 저장[x][y] = n
    해당 배열을 -1로 초기화하면 visit 배열의 기능도 수행 가능

*/

import java.io.*
import java.util.StringTokenizer
import java.util.Queue
import java.util.LinkedList
private lateinit var disit : Array<Array<Int>>
private lateinit var board : Array<Array<Int>>
private var queue : Queue<Pair<Int, Int>> = LinkedList()
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    board = Array(n){Array(m){0}}
    disit = Array(n){Array(m){-1}}
    for(i in 0 until n){
        val row = br.readLine()
        board[i] = Array(m){k -> row[k].toString().toInt()}
    }

    bfs(Pair(0, 0), n, m)
    bw.write("${disit[n-1][m-1]}\n")
   
    bw.flush()
    bw.close()
    br.close()
}

private fun bfs(start : Pair<Int, Int>, n : Int, m : Int){
    queue.offer(start)
    disit[start.first][start.second] = 1
    printNode(start)

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

            // 방문했거나, 방문할수 없는 노드인 경우
            if(board[nx][ny] == 0 || disit[nx][ny] != -1){
                continue
            }

            queue.offer(Pair(nx, ny))
            disit[nx][ny] = disit[node.first][node.second] + 1
            printNode(nx, ny)
        }
    }
}

private fun printNode(node : Pair<Int, Int>){
    printNode(node.first, node.second)
}
private fun printNode(nx : Int, ny : Int){
    print("node[$nx, $ny] : ${disit[nx][ny]}\n")
}
