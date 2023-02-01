/*
    백준 11780
    바킹독님 알고리즘 강의 중 플로이드 알고리즘 단원 연습문제
    https://www.acmicpc.net/problem/11780
    
    gold 2

    N개의 도시
    한 도시에서 출발해, 다른 도시에 도착하는 M개의 버스
    각 버스는 한번 탈때 비용이 존재

    모든 도시(A, B)에 대해 도시 A에서 B로 가는데 필요한 최솟값을 구하시오

    1 ≤ n ≤ 100
    1 ≤ m ≤ 100,000

    먼저 n개의 줄을 출력
    i번째 줄의 j번째 숫자는 i에서 j로 가는데 필요한 최소 비용
    - i에서 j로 갈수없다면 0을 출력

    그 다음, n*n개의 줄을 출력
    1. i*n + j번째 줄에는 도시 i에서 도시 j로 가는 최소비용에 포함되어있는 도시의 개수 k를 출력
    - i에서 j로 갈 수 없다면 0을 출력
    
    2. 그 다음, 개행 없이 도시 i에서 도시j로 가는 경로를 공백으로 구분해 출력
    - 이때, 도시 i와 도시 j도 출력
*/
/*
    #제출
    1. 성공

    #헷갈린부분
    path를 갱신해줄때
    i를 경유해서 가는게 더 효율적이니, x->y의 경로를 x->i로 변경해줘야 하기에
    path[x][y] = path[x][i]로 갱신을 해줘야 하는데, 
    path[x][y] = i 로 갱신해줬던 부분
*/

fun main(){
    Solution11780().solve()
}
class Solution11780 {
    private lateinit var board: Array<Array<Int>>
    private lateinit var path: Array<Array<Int>>
    private val INF = 1000000000
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
    
        br.readLine().toInt().let { n ->
            board = Array(n+1){ Array(n+1){ INF } }
            path = Array(n+1){ Array(n+1){ 0 } }
        }

        repeat(br.readLine().toInt()) { _ ->
            br.readLine().split(' ').map{ it.toInt() }.let {
                board[it[0]][it[1]] = Math.min(it[2], board[it[0]][it[1]])
                path[it[0]][it[1]] = it[1]
            }
        }

        for(x in 1 until board.size){
            for(y in 1 until board.size) {
                if(x == y) board[x][y] = 0
            }
        }

        for(i in 1 until board.size) {
            for(x in 1 until board.size) {
                for(y in 1 until board.size){
                    val min = Math.min(board[x][i] + board[i][y], board[x][y])
                    if(min != board[x][y]){
                        path[x][y] = path[x][i] // i를 경유해서 가는게 더 효율적이니, x->y의 경로를 x->i로 변경
                        board[x][y] = min
                    }
                }
            }
        }

        for(x in 1 until board.size){
            for(y in 1 until board.size) {
                bw.write("${if(board[x][y] == INF) 0 else board[x][y]} ")
            }
            bw.write("\n")
        }

        for(start in 1 until board.size) {
            for(to in 1 until board.size) {
                if(start == to){
                    bw.write("0\n")
                    continue
                }
                val sb = StringBuilder()
                var from = start
                var count = 1
                sb.append(" $from")
                while(from != to) {
                    from = path[from][to]
                    if(from == 0){
                        sb.setLength(0)
                        count = 0
                        break
                    }
                    sb.append(" $from")
                    count++
                }
                bw.write("${count}${sb}\n")
            }
        }

        bw.write("board debug\n")
        for(x in 1 until board.size){
            for(y in 1 until board.size) {
                bw.write("${board[x][y]} ")
            }
            bw.write("\n")
        }
        bw.write("board debug finish\n")
        bw.write("\npath debug\n")
        for(x in 1 until path.size){
            for(y in 1 until path.size) {
                bw.write("${path[x][y]} ")
            }
            bw.write("\n")
        }
        bw.write("path debug finish\n")
    
        bw.flush()
        bw.close()
        br.close()
    }
}