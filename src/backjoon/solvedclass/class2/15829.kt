/*
    백준 16829
    solved.ac class2** 문제
    https://www.acmicpc.net/problem/15829

    bronze 2

    Hashing

    a, b, c, d.. 알파벳을 1부터 26까지의 고유한 번호를 부여
    각 자리수를 i라고 할때 31^i를 각 번호에 곱
    모든 자리수를 숫자로 치환한 뒤 더하여, 1234567891로 나눔
*/

/*
    제출

    1. 50점
    - 값을 더하는 도중 범위로 인해 터지는걸 방지하기위해 Long타입으로 변경

    2. 50점
    - 1234567891의 나머지를 구하는 과정(MOD)을 매 연산마다 진행

    3. 50점
    - 맨 마지막에 더했을때 1234567891를 넘길 수 있는 경우 반영

    4. 50점
    - pow 직접 구현(오버플로 발생 가능성때문에 MOD를 적용해줘야 함)

    5. 50점
    - pow 연산 시 마다 MOD 적용

    6. 100점
*/

fun main() {
    Solution16829().solve()
}

class Solution16829 {
    private val M = 1234567891
    fun solve() {
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        val s = br.readLine()

        var sum: Long = 0
        s.forEachIndexed { i, v -> 
            val aCode = v.toInt() - 96
            val r = pow(31, i, M)
            println("$v => ${aCode*r}")
            sum += (aCode*r).toLong() % M
        }
        sum %= M
        bw.write("${sum.toInt()}\n")

        bw.flush()
        bw.close()
        br.close()
    }

    private fun pow(n: Int, i: Int, mod: Int): Long {
        var value: Long = 1
        var cnt = 0
        while(cnt < i) {
            value = value * n % mod
            cnt++
        }
        return value
    }
}