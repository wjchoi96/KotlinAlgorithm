// gold 5
/*
    네이버 카페 문제 50선 중 dfs 유형

    적록색약
    빨강색과 초록색의 차이를 거의 느끼지 못한다

    N*N 의 board
    R, G, B 중 하나를 색칠한 그림
    그림은 몇개의 구역으로 이루어져있고, 한 구역은 같은 색으로 이루어져있다

    색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다

    일반인이 본 구역의 수와
    적록색약인 사람이 본 구역의 수를 구하라

    1<=N<=100

    =========================================
    1. 탐색을 두번 돌리는게 맞겠지
    2. board 순회는 하나로 가능하게 하자

    stack 2개
    visit 2개
*/


import java.util.Stack
import java.io.*
private val dx = arrayOf(1, 0, -1, 0)
private val dy = arrayOf(0, 1, 0, -1)
private var stack = Stack<Pair<Int, Int>>()
private var stackColor = Stack<Pair<Int, Int>>()
private lateinit var board : Array<Array<Char>>
private lateinit var visit : Array<Array<Boolean>>
private lateinit var visitColor : Array<Array<Boolean>>
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()
    board = Array(n) { Array(n){'.'} }
    visit = Array(n) { Array(n){false} }
    visitColor = Array(n) { Array(n){false} }
    for(x in 0 until n){
        val row = br.readLine()
        board[x] = Array(n){ i -> row[i]}
    }

    var count = 0
    var colorCount = 0
    for(x in 0 until n){
        for(y in 0 until n){
            if(visit[x][y] == false){
                dfs(Pair(x, y), n)
                count++
            }
            if(visitColor[x][y] == false){
                dfsColor(Pair(x, y), n)
                colorCount++
            }
        }
    }
    bw.write("$count $colorCount\n")


    bw.flush()
    bw.close()
    br.close()
}

/*
    두번째 방법
    갑자기 생각난 개선안
    탐색을 진행하면서 R을 만나면 G로 바꿔버리면(만나면 무조건 바꾸는게 아닌, R구역 탐색시 완료된 node 를 바꿔줘야할듯)
    첫번째 탐색은 일반인 탐색
    두번째 탐색은 적록색약 탐색이 되지않을까?

    코드의 양은 줄고, 변수도 덜쓰는데 board 의 순회가 두번 필요함
*/
private fun main2(n : Int){
    var count = 0
    var colorCount = 0
    for(x in 0 until n){
        for(y in 0 until n){
            if(visit[x][y] == false){
                dfs2(Pair(x, y), n)
                count++
            }
        }
    }
    visit = Array(n) { Array(n){false} }
    for(x in 0 until n){
        for(y in 0 until n){
            if(visit[x][y] == false){
                dfs2(Pair(x, y), n)
                colorCount++
            }
        }
    }
}
private fun dfs2(start : Pair<Int, Int>, n : Int){
    val color = board[start.first][start.second]
    stack.push(start)
    visit[start.first][start.second] = true
    while(!stack.isEmpty()){
        val node = stack.pop()
        if(board[node.first][node.second] == 'R'){
            board[node.first][node.second] = 'G'
        }
        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]
            if(nx<0 || nx>=n || ny<0 || ny>=n){
                continue
            }
            if(visit[nx][ny]==true || board[nx][ny] != color){
                continue
            }
            stack.push(Pair(nx, ny))
            visit[nx][ny] = true            
        }
    }
}

/*
    첫번째 방법
    일반 dfs와 색약dfs를 따로 작성해서 board를 한번만 순회하며 동시에 진행
*/
private fun dfs(start : Pair<Int, Int>, n : Int){
    val color = board[start.first][start.second]
    stack.push(start)
    visit[start.first][start.second] = true
    while(!stack.isEmpty()){
        val node = stack.pop()
        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]
            if(nx<0 || nx>=n || ny<0 || ny>=n){
                continue
            }
            if(visit[nx][ny]==true || board[nx][ny] != color){
                continue
            }
            stack.push(Pair(nx, ny))
            visit[nx][ny] = true            
        }
    }
}
/*
    color 가 'B' 일땐 같은 색깔이 아니면 건너뛴다
    color 가 'R' or 'G' 일땐 'B' 색깔만 건너뛴다
*/
private fun dfsColor(start : Pair<Int, Int>, n : Int){
    val color = board[start.first][start.second]
    stackColor.push(start)
    visitColor[start.first][start.second] = true
    while(!stackColor.isEmpty()){
        val node = stackColor.pop()
        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]
            if(nx<0 || nx>=n || ny<0 || ny>=n){
                continue
            }
            if(visitColor[nx][ny]==true){
                continue
            }
            
            if(color == 'B' && board[nx][ny] != color){
                continue
            }else if(color != 'B' && board[nx][ny] =='B'){
                continue
            }
            stackColor.push(Pair(nx, ny))
            visitColor[nx][ny] = true            
        }
    }
}