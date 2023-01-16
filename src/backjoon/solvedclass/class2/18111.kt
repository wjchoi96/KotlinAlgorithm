/*
    백준 18111
    solved.ac class2** 문제
    https://www.acmicpc.net/problem/18111

    sliver 2

    세로N, 가로M
    왼쪽위(0, 0)
    
    2가지 동작을 수행 가능
    1. 좌표(i, j)의 가장 위에 있는 블록을 제거하여 인벤토리에 넣음
    2. 인벤토리에서 블록 하나를 꺼내 좌표(i, j)의 가장 위에 있는 블록 위에 놓음

    1번 작업은 2초, 2번 작업은 1초가 소모됨

    작업을 시작할때 인벤토리에는 B개의 블록이 들어있음
    땅의 높이는 256블록을 초과할 수 없음, 음수가 될 수 없음

    땅을 평탄화 시키는데 걸리는 최소 시간, 그때의 땅의 높이를 구하라
*/
/*
    1 <= M, N <= 500
    0 <= B <= 6.4 * 10^7
    땅의 높이는 256블록을 초과할 수 없음, 음수가 될 수 없음
*/
/*
    - 현재 땅의 최소높이와 최대 높이를 구함
    - 최소높이에서 최대높이로 가며, 모든 높이에 대한 나라시 작업에 대한 시간체크를 진행
    - 최소값을 출력

    필요한 함수
    1. narasi(h: Int, inventory: Int, area: Array<Array<Int>>): Int 
    - 목표 높이(h)로 area를 나라시하는데 걸리는 sec 리턴
    - 블록의 개수가 부족해 나라시가 불가능하다면 -1리턴

*/
/*
    제출
    1. 틀렸습니다(1%)
    - narasi 파낸 블록을 인벤토리에 넣는 계산을 실수

    2.성공
*/

fun main() {
    Solution18111().solve()
}

class Solution18111 {

    fun solve() {
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
        val (n, m, b) = br.readLine().split(' ').map{ it.toInt() }
        val board: Array<Array<Int>> = Array(n){ Array(m) { 0 } }
        repeat(n) { board[it] = br.readLine().split(' ').map{ it.toInt() }.toTypedArray() }

        var minBlock = Int.MAX_VALUE
        var maxBlock = Int.MIN_VALUE
        for(x in 0 until board.size) {
            for(y in 0 until board[x].size) {
                if(board[x][y] < minBlock) {
                    minBlock = board[x][y]
                }
                if(board[x][y] > maxBlock) {
                    maxBlock = board[x][y]
                }
            }
        }

        var resultSec = Int.MAX_VALUE
        var resultHeight = 0
        for(h in minBlock..maxBlock) {
            val res = narasi(h, b, board)
            println("h[$h], res[$res]sec")
            if(res >= 0){
                if(res > resultSec){
                    continue
                }else {
                    resultSec = res
                    resultHeight = h
                }
            }
        }

        bw.write("$resultSec $resultHeight\n")


        bw.flush()
        bw.close()
        br.close()
    }

    private fun narasi(h: Int, inventory: Int, area: Array<Array<Int>>): Int {
        var sec = 0
        var invenBlock = inventory
        for(x in 0 until area.size) {
            for(y in 0 until area[x].size) {
                when {
                    area[x][y] < h -> {
                        sec += h - area[x][y]
                        invenBlock -= h - area[x][y]
                        // area[x][y] = h
                    }
                    else -> {
                        sec += (area[x][y] - h) * 2
                        invenBlock += area[x][y] - h
                        // area[x][y] = h
                    }
                }
            }
        }
        return if(invenBlock >= 0) sec else -1
    }
    
}