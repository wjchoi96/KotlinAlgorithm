package src.backjoon.algorithmtype.bfs//gold4
/*
    바캉독님 bfs강의에 나온 연습문제

    나랑 조금 다른 풀이
    https://www.acmicpc.net/board/view/82153
    => 나는 불과 지훈이를 동시에 진행시키고, 모든 bfs가 완료된 후 탈출구들의 disit를 비교해보았는데
    이분은 불을 먼저 내고, 지훈이를 보내면서 체크
    1. 지훈이가 탈출구를 만나면 escape 완료한것 처리
    2. 진행루트에 지훈이보다 불이 먼저 나있다면 continue 조건 추가
    3. 탈출못하고 bfs가 종료되면 impossible 처리

    미로탈출 + 불
    지훈이와 불은 매 분마다 한칸씩 이동

    불은 4방향 확산
    탈출구 : 미로의 가장자리에 접한 공간

    # : qur
    . : 지나갈수 있는 공간
    J : 지훈이 초기 위치
    F : 불이난 공간

    queue가 2개가 필요한가?

    fire queue
    person queue

    탈출구의 해당하는 disit를 확인했을때
    지훈이의 disit가 더 빠르면 탈출 가능
    fire가 더 빠르거나 같다면 탈출 불가능

    1 <= m,n <= 1000
    1초

    제출
    1. 틀렸습니다(60~70?)
    반례
    4 4
    ####
    JF.#
    #..#
    #..#
    정답 : 1
    출력 : IMPOSSIBLE
    => 바로 출구로 나갈 수 있는 경우를 체크못함

    2. 틀렸습니다(71%)
    반례
    4 4
    ###F
    #J.#
    #..#
    #..#
    정답 : 3
    출력 : IMPOSSIBLE
    => 불이 움직일수 없는 경우를 고려해야한다

    3. 틀렸습니다(7%)
    => 2번을 해결했더니, 퍼센테이지가 깎여서 실패
    반례
    5 5
    ....F
    ...J#
    ....#
    ....#
    ...#.
    정답 : 4
    출력 : 0
    => 불이 못가는곳을 처리했더니, 지훈이도 못가는곳이 정답처리가 되어버렸다


    [refactor] 제출
    1. 틀렸습니다(7%쯤)
    1-3 반례로 틀림
    
    2. 틀렸습니다(71%쯤)
    1-2 반례로 틀림

    3. 성공
*/

import java.util.StringTokenizer
import java.util.LinkedList
import java.util.Queue
import java.io.*
private const val wall = '#'
private const val road = '.'
private lateinit var board : Array<Array<Char>>
private lateinit var fDisit : Array<Array<Int>>
private lateinit var jDisit : Array<Array<Int>>
private val fQueue : Queue<Pair<Int, Int>> = LinkedList()
private val jQueue : Queue<Pair<Int, Int>> = LinkedList()
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    board = Array(n){ Array(m){ '.' } }
    fDisit = Array(n){Array(m){-1}}
    jDisit = Array(n){Array(m){-1}}

    for(i in 0 until n){
        val row = br.readLine()
        for(j in 0 until m){
            board[i][j] = row[j]
            when(board[i][j]){
                'J' -> {
                    jQueue.offer(Pair(i, j))
                    jDisit[i][j] = 0
                }
                'F' -> {
                    fQueue.offer(Pair(i, j))
                    fDisit[i][j] = 0
                }
            }
        }
    }

    val dx = arrayOf(1, 0, -1, 0)
    val dy = arrayOf(0, 1, 0, -1)
    while(!fQueue.isEmpty()){
        val node = fQueue.poll()
        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]
            if(nx<0 || nx>= n || ny<0 || ny>=m){
                continue
            }
            // 벽이거나, 방문한 노드라면 건너뛴다
            if(board[nx][ny] == wall || fDisit[nx][ny] >=0){
                continue
            }
            fQueue.offer(Pair(nx,ny))
            fDisit[nx][ny] = fDisit[node.first][node.second] + 1
        }
    }
    //fire 의 진행기록이 fDisit에 담겨있다

    while(!jQueue.isEmpty()){
        val node = jQueue.poll()
        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]
            
            if(nx<0 || nx>= n || ny<0 || ny>=m){
                // 탈출구 도착. 불에 번지기전에 도착한거라면 완료 -> 불에 진행상황을 먼저 체크한다
                // 불의 진행상황을 먼저 체크했더니, index out of range exception 이 발생할 수 있군
                // 여긴 size가 밖인곳이니 +1을 안해줘도 되는구나
                // 여긴 board 를 벗어난 곳이니 여기서 불 disit를 체크할 필요가 없었구나
                bw.write("${jDisit[node.first][node.second] + 1}") 
                bw.flush()
                bw.close()
                br.close()
                return
            }

            //벽이거나, 지훈이보다 불이 먼저 도착한곳이라면 건너뛴다
            //내가 기존에 방문했던곳인지 체크하는 코드도 누락
            if(board[nx][ny] == wall || jDisit[nx][ny] >=0){
                continue
            }
            //jDisit[nx][ny] 여기선 초기화값이 들어가있어서 비교할수 없다
            //fire가 방문하지 못한곳은 초기화값인 -1이 들어가있기때문에 제외해준다
            if(fDisit[nx][ny] != -1 && (fDisit[nx][ny] <= jDisit[node.first][node.second] + 1)){
                continue
            }
            
            
            jQueue.offer(Pair(nx, ny))
            jDisit[nx][ny] = jDisit[node.first][node.second] + 1
        }
    }
    
    bw.write("IMPOSSIBLE\n")
    bw.flush()
    bw.close()
    br.close()

}

/*
    solve 1
*/
private fun solve1(){
     val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    board = Array(n){ Array(m){ '.' } }
    fDisit = Array(n){Array(m){-1}}
    jDisit = Array(n){Array(m){-1}}

    val exits = ArrayList<Pair<Int, Int>>()
    for(i in 0 until n){
        val row = br.readLine()
        for(j in 0 until m){
            board[i][j] = row[j]
            when(board[i][j]){
                'J' -> {
                    jQueue.offer(Pair(i, j))
                    jDisit[i][j] = 0
                    if(i == 0 || i == n-1 || j == 0 || j == m-1){
                        exits.add(Pair(i,j))
                    }
                }
                'F' -> {
                    fQueue.offer(Pair(i, j))
                    fDisit[i][j] = 0
                }
                '.' -> {
                    if(i == 0 || i == n-1 || j == 0 || j == m-1){
                        exits.add(Pair(i,j))
                    }
                }
            }
        }
    }

    while(!jQueue.isEmpty() || !fQueue.isEmpty()){
        val fNode : Pair<Int, Int>? = fQueue.poll()
        val jNode : Pair<Int, Int>? = jQueue.poll()
        if(fNode != null){
            bfs(fNode, fDisit, fQueue)
            // printNode(fNode, "fNode")
        }
        if(jNode != null){
            bfs(jNode, jDisit, jQueue)
            // printNode(jNode, "jNode")
        }
    }

    var min = Int.MAX_VALUE
    for(exit in exits){
        if(jDisit[exit.first][exit.second] < fDisit[exit.first][exit.second]){
            min = Math.min(min, jDisit[exit.first][exit.second])
        }else if(jDisit[exit.first][exit.second] >= 0 && fDisit[exit.first][exit.second] < 0){
            // 불이 해당 탈출구에 도달 못한 경우 => 지훈이는 도착한 출구여야한다
            min = Math.min(min, jDisit[exit.first][exit.second])
        }
    }
    if(min == Int.MAX_VALUE){
        bw.write("IMPOSSIBLE\n")
    }else{
        bw.write("${min+1}\n") // exit에서 한칸 더 가야 미로를 탈출한것으로 간주
    }
   
    bw.flush()
    bw.close()
    br.close()

}
private fun bfs(node : Pair<Int, Int>, disit : Array<Array<Int>>, queue : Queue<Pair<Int, Int>>){
    val dx = arrayOf(1, 0, -1, 0)
    val dy = arrayOf(0, 1, 0, -1)

    for(i in 0 until 4){
        val nx = node.first + dx[i]
        val ny = node.second + dy[i]
        if(nx < 0 || nx >= board.size || ny < 0 || ny >= board[0].size){
            continue
        }

        if(disit[nx][ny] >= 0 || board[nx][ny] == wall){
            continue
        }

        disit[nx][ny] = disit[node.first][node.second] + 1
        queue.offer(Pair(nx, ny))
    }
}

private fun printNode(node : Pair<Int, Int>, label : String? = null){
    printNode(node.first, node.second, label)
}
private fun printNode(nx : Int, ny : Int, label : String? = null){
    print("${label ?: ""} node[$nx, $ny]\n")
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
