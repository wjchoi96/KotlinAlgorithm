/*
    백준 1764
    solved.ac의 class3+ 문제
    https://www.acmicpc.net/problem/1764

    silver 4

    2개의 명단이 주어짐
    - 들어보지 못한 사람의 명단
    - 본적 없는 사람의 명단
    - 각 명단에는 중복되는 데이터가 없음

    듣도 보도 못한 사람의 명단을 구하시오

    N, M은 500,000 이하의 자연수
    O(NM)이면 250000000000이기에 시간초과

    Hash를 사용해 O(N)에 수행해보자

    #제출
    1. 성공

*/

fun main(){
    Solution1764().solve()
}
class Solution1764 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (n, m) = br.readLine().split(' ').map{it.toInt()}
        val nSet = hashSetOf<String>().apply {
            repeat(n) { add(br.readLine()) }
        }
        val mSet = hashSetOf<String>().apply {
            repeat(m) { add(br.readLine()) }
        }

        val result = nSet.filter{ mSet.contains(it) }.sorted()
        bw.write("${result.size}\n")
        result.forEach{ bw.write("$it\n") }
    
        bw.flush()
        bw.close()
        br.close()
    }
}