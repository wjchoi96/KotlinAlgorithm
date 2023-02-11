/*
    백준 17219
    solved.ac class 3문제
    https://www.acmicpc.net/problem/17219

    sliver 4

    메모장에 사이트의 주소와 비밀번호를 저장
    메모장에서 비밀번호를 찾는 프로그램을 만듬

    1 ≤ N ≤ 100,000 => 사이트 수
    1 ≤ M ≤ 100,000 => 비밀번호를 찾으려는 사이트의 수
*/
/*
    그냥 HashMap에 저장

    #제출
    1. 성공

*/

fun main(){
    Solution17219().solve()
}
class Solution17219 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (n, m) = br.readLine().split(' ').map{it.toInt()}
        val map = HashMap<String, String>()

        repeat(n) {
            br.readLine().split(' ').let {
                map[it[0]] = it[1]
            }
        }

        repeat(m) {
            bw.write("${map[br.readLine()]}\n")
        }
    
        bw.flush()
        bw.close()
        br.close()
    }
}