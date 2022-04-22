//sliver1
/*
    네이버 카페 문제50선 중 dfs 유형

    안전영역

    각 지역의 높이가 담긴 2차원 행렬이 주어진다
    
    비의 양이 주어졌을때, 물에 잠기지 않은 안전한 지역들이 연결된곳을 안전영역이라 한다

    비의 양에 따라 생기는 안전영역의 개수는 달라지는데, 안정영역의 최대개수를 구하시오

    2 <= N <= 100
    1 <= h <= 100
    1초


    ============================================================
    
    1. 값을 입력받으며 현재 최대 높이를 구한다
    2. 1 ~ 최대높이까지의 비를 대입해보며 dfs를 수행(dfs 유형을 학습중이라서 dfs로 수행)
    3. 해당 dfs수행 후 안전영역의 개수를 count 배열에 저장
*/


import java.util.Stack
import java.util.StringTokenizer
import java.io.*
private val dx = arrayOf(1, 0, -1, 0)
private val dy = arrayOf(0, 1, 0, -1)
private var stack = Stack<Pair<Int, Int>>()
private lateinit var board : Array<Array<Int>>
private lateinit var visit : Array<Array<Boolean>>
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()
    board = Array(n){Array(n){-1}}
    var maxRain : Int = Int.MIN_VALUE
    for(x in 0 until n){
        val st = StringTokenizer(br.readLine())
        for(y in 0 until n){
            board[x][y] = st.nextToken().toInt()
            maxRain = Math.max(maxRain, board[x][y])
        }
    }

    val rains = Array(maxRain+1) {i->i}
    var maxSafeArea : Int = Int.MIN_VALUE

    for(rain in rains){
        visit = Array(n){Array(n){false}}
        var safeArea = 0
        for(x in 0 until n){
            for(y in 0 until n){
                if(board[x][y] > rain && visit[x][y] == false){
                    bfs(Pair(x, y), rain, n)
                    safeArea++
                }
            }
        }
        maxSafeArea = Math.max(maxSafeArea, safeArea)
    }
   
    bw.write("$maxSafeArea\n")

    bw.flush()
    bw.close()
    br.close()
}

private fun bfs(start : Pair<Int, Int>, rain : Int, n : Int){
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
            // 이미 방문했거나, 비에 잠긴 지역은 건너뛴다
            if(visit[nx][ny] == true || board[nx][ny] <= rain){
                continue
            }
            stack.push(Pair(nx, ny))
            visit[nx][ny] = true
        }
    }
}