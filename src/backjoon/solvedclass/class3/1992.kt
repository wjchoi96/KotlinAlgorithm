/*
    백준 1992
    solved.ac class 3
    https://www.acmicpc.net/problem/1992

    sliver 1

    board에 0과 1이 존재
    - board가 0으로만 이루어져있다면 0으로 압축
    - board가 1로만 이루어져있다면 1로 압축
    - board가 0과 1이 섞여있어 전체를 하나로 표현하지 못한다면 
    왼쪽위, 왼쪽아래, 오른쪽위, 오른쪽아래 4개로 분할하여 압축을 진행

    N*N의 board가 제공
    압축된 결과를 출력

    N 2의 제곱수
    1 ≤ N ≤ 64
*/
/*
    #제출
    - 전체가 1, 0인 경우에는 괄호를 포함해야 하는지, 아닌지 애매

    1. 성공
    - 전체가 1, 0인 경우에는 괄호가 포함되지 않아야 하는게 정답
    
*/

fun main(){
    Solution1992().solve()
}
class Solution1992 {
    private lateinit var board: Array<Array<Int>>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        board = Array(n){Array(n){0}}
        repeat(n) {
            board[it] = br.readLine().toCharArray().map{it.toString().toInt()}.toTypedArray()
        }

        val res = compressBoard(0, 0, n)
        bw.write("$res\n")
        bw.flush()
        bw.close()
        br.close()
    }

    private fun compressBoard(startX: Int, startY: Int, n: Int): String {
        if(n == 1) { // 한칸인 경우가 baseCondition
            return board[startX][startY].toString()
        }
        var whiteCnt = 0
        var blackCnt = 0
        for(x in startX until startX+n) {
            for(y in startY until startY+n) {
                when (board[x][y]) {
                    0 -> whiteCnt++
                    1 -> blackCnt++
                }
            }
        }
        return if(whiteCnt == 0){
            "1"
        }else if(blackCnt == 0){
            "0"
        }else {
            "(" + 
                "${compressBoard(startX, startY, n/2)}" + 
                "${compressBoard(startX, startY+n/2, n/2)}" + 
                "${compressBoard(startX+n/2, startY, n/2)}" + 
                "${compressBoard(startX+n/2, startY+n/2, n/2)}" + 
            ")"
        }
    }
}