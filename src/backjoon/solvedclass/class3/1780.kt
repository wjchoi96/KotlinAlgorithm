/*
    백준 1780
    solved.ac class 3문제
    https://www.acmicpc.net/problem/1780   
    sliver 2

    N*N 크기의 행렬
    -1, 0, 1 중 하나가 저장됨

    1. 종이가 모두 같은 수로 되어있다면 이 종이를 그대로 사용
    2. (1)이 아닌 경우, 종이를 같은 크기의 종이 9개로 자르고, 각각의 잘린 종이에 대해 (1)을 반복

    -1로만 채워진 종이, 0으로만 채워진 종이, 1로만 채워진 종이의 개수를 구하시오

    1 <= N <= 3^7
    N은 3^k 꼴
*/
/*
    분할정복 문제

    baseCondition
    - size가 1일때

    return
    Triple로 현재 범위에 대해 각 종이의 개수 리턴
    -1의 개수, 0의 개수, 1의 개수를 리턴

    현재 범위가 1가지 종이로만 채워진 경우, 이전 범위에 합산하여 리턴
    아니라면, 범위를 쪼개서 재귀 호출

    #제출
    1. 성공
*/

fun main(){
    Solution1780().solve()
}
class Solution1780 {
    private lateinit var board: Array<Array<Int>>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        board = Array(n){Array(n){0}}
        repeat(n){
            board[it] = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
        }

        val res = getCount(0, 0, n, Triple(0, 0, 0))
        bw.write("${res.first}\n${res.second}\n${res.third}")

        bw.flush()
        bw.close()
        br.close()
    }

    private fun getCount(x: Int, y: Int, size: Int, v: Triple<Int, Int, Int>): Triple<Int, Int, Int> {
        // println("getCount x[$x], y[$y], size[$size], v[$v]")
        if(size <= 1){
            return v + when(board[x][y]) {
                -1 -> Triple(1, 0, 0)
                0 -> Triple(0, 1, 0)
                else -> Triple(0, 0, 1)
            }
        }

        var isSameContent = true
        val item = board[x][y]
        for(i in x until x+size){
            for(j in y until y+size){
                if(item != board[i][j]){
                    isSameContent = false
                    break
                }
            }
        }

        return v + when(isSameContent) {
            true -> {
                when(board[x][y]) {
                    -1 -> Triple(1, 0, 0)
                    0 -> Triple(0, 1, 0)
                    else -> Triple(0, 0, 1)
                }
            }
            else -> {
                val divideSize = size/3
                getCount(x, y, divideSize, v) + 
                getCount(x, y+divideSize, divideSize, v) + 
                getCount(x, y+divideSize*2, divideSize, v) +
                getCount(x+divideSize, y, divideSize, v) + 
                getCount(x+divideSize*2, y, divideSize, v) + 
                getCount(x+divideSize, y+divideSize, divideSize, v) + 
                getCount(x+divideSize, y+divideSize*2, divideSize, v) +
                getCount(x+divideSize*2, y+divideSize, divideSize, v) + 
                getCount(x+divideSize*2, y+divideSize*2, divideSize, v)
            }
        }
        
    }

    operator fun Triple<Int, Int, Int>.plus(other: Triple<Int, Int, Int>): Triple<Int, Int, Int> {
        return Triple(
            this.first + other.first,
            this.second + other.second,
            this.third + other.third
        )
    }
}