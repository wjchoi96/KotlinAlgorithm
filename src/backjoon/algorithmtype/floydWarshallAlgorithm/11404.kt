/*
    백준 11404
    바킹독님 알고리즘 강의 중 플로이드 단원 연습문제
    https://www.acmicpc.net/problem/11404

    gold 4

    N개의 도시
    한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스
    - 각 버스는 사용할떄마다 비용이 존재

    모든 도시의 쌍(A, B)에 대해 도시 A에서 B로 가는 최소 비용을 구하시오
    
    2 ≤ n ≤ 100
    1 ≤ m ≤ 100,000
    1 <= 비용 <= 100000

    시작도시와 도착도시를 연결하는 노선은 하나가 아닐 수 있음

*/
/*
    - 인접행렬로 그래프를 구성
    - 삼중 for문으로 플로이드 알고리즘 구현

    - board에 처음 입력값을 넣어줄때 board[i][j], board[j][i] 모두 값을 대입해줘야 한다 생각했는데
    그렇게하니까 답이 이상하게 나오는 현상이 발생

    - 1에서 5를 가는 버스와, 5에서 1을 가는 버스가 다른 문제였음
    - 방향이 존재하는 그래프였네..

    #제출
    1. 성공
*/

fun main(){
    Solution11404().solve()
}

class Solution11404 {
    private lateinit var board: Array<Array<Int>>
    private val INF = 1000000000 // 비용의 범위는 100000이하이니, 100000초과된 값으로 초기값을 잡아줌
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        val m = br.readLine().toInt()
        board = Array(n+1){ Array(n+1) { INF } }

        repeat(m) { _ -> 
            br.readLine().split(' ').map{ it.toInt() }.let {
                board[it[0]][it[1]] = Math.min(board[it[0]][it[1]], it[2])
            }
        }
        for(i in 1..n) {
            board[i][i] = 0
        }
        printBoard(-1)

        for(i in 1..n) {
            for(x in 1..n) {
                for(y in 1..n) {
                    board[x][y] = Math.min(board[x][i] + board[i][y], board[x][y])
                }   
            }
            printBoard(i)
        }

        for(i in 1..n){
            for(j in 1..n) {
                bw.write("${if(board[i][j] == INF) 0 else board[i][j]} ")
            }
            bw.write("\n")
        }
    
        bw.flush()
        bw.close()
        br.close()
    }

    private fun printBoard(depth: Int) {
        println("board debug[$depth]")
        for(i in 1 until board.size){
            for(j in 1 until board[i].size) {
                print("${if(board[i][j] == INF) 0 else board[i][j]} ")
            }
            println("")
        }
        println("board debug finish")
    }
}