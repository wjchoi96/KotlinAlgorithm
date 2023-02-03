/*
    백준 2630
    solved.ac class3+ 문제
    https://www.acmicpc.net/problem/2630

    silver 2

    여러 정사각형 칸으로 이루어진 정사각형 종이가 존재
    - N*N
    - 각 칸들은 하얀색 or 파란색
    - 종이를 일정한 규칙에 따라 잘라, 다양한 크기를 가진 정사각형 모양의 하얀색 or 파랑색 색종이를 만드려 함


    N은 2^k 일때, 종이를 자르는 규칙은 다음과 같음
    - k는 1이상 7이하의 자연수

    1. 전체 종이가 모두 같은 색이 아니라면, 가로와 세로의 중간 부분을 잘라서
    4개의 N/2 * N/2색종이로 나눔

    2. 나눠진 색종이도 1번과 마찬가지로, 모두 같은 색이 아니라면 가로와 세로의 중간부분을 잘라 4개로 나눔
    N/4 * N/4색종이가 될것

    3. 하나의 정사각형 칸이 되거나, 잘라진 종이가 모두 하얀색 or 파란색인 경우까지 반복

    N은 2, 4, 8, 16, 32, 64, 128중 하나
    잘라진 하얀색 색종이와 파랑색 색종이의 개수를 구하시오
*/
/*
    N은 최대 2^7

    당장 쉽게 생각나는것
    1. 색종이를 검사
    2. 쪼갠다
    3. 쪼개진 색종이를 검사
    4. 또 쪼갠다
    5. 끝까지 반복
    => 해당 경우 최악의 시간복잡도?
    7번 쪼갬

    재귀, 분할정복 문제라는 힌트를 봄
    x, y, n을 입력받고, n*n범위의 색종이의 흰색종이, 파랑종이 개수를 리턴하는 함수 작성
    - 이때, 자신 범위의 모든 색종이가 같은 색이라면 baseCondition
    - 현재 범위를 체크하는 로직의 시간복잡도가 거슬리는데
    O(n^3)까지 버티는 범위이니 돌려보자

    #제출
    1. 성공

*/

fun main(){
    Solution2630().solve()
}
class Solution2630 {
    private lateinit var board: Array<Array<Int>>
    private val BLUE = 1
    private val WHITE = 0
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        board = Array(n){ Array(n){0} }
        repeat(n) { x ->
            board[x] = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
        }

        val res = getColorCount(0, 0, n)
        bw.write("${res.first}\n${res.second}\n")

        bw.flush()
        bw.close()
        br.close()
    }

    operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) = (this.first + other.first) to (this.second + other.second)

    // white, blud 색상 개수 리턴
    private fun getColorCount(x: Int, y: Int, n: Int): Pair<Int, Int> {
        var blueCount = 0
        var whiteCount = 0
        for(i in x until x+n) {
            for(j in y until y+n) {
                when (board[i][j]) {
                    WHITE -> whiteCount++
                    BLUE -> blueCount++
                }
            }
        }
        val res = if(blueCount == 0) {
            1 to 0
        }else if(whiteCount == 0){
            0 to 1
        }else {
            getColorCount(x, y, n/2) + getColorCount(x+n/2, y, n/2) + getColorCount(x, y+n/2, n/2) + getColorCount(x+n/2, y+n/2, n/2)
        }
        println("($x, $y) => size[$n] => res[$res]")
        return res
    }
}