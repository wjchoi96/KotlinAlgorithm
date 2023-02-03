/*
    백준 11723
    solced.ac class3+ 문제
    https://www.acmicpc.net/problem/11723

    silver 5

    비어있는 공집합 S
    아래 연산들을 수행하는 프로그램을 작성

    add x
    - S에 x를 추가
    - x가 이미 있다면 무시

    remove x
    - S에서 x를 제거
    - x가 없는 경우 연산을 무시

    check x
    - S에 x가 있으면 1을, 없으면 0을 출력

    toggle x
    - S에 x가 있으면 X를 제거하고, 없으면 X를 추가

    all
    - S를 {1, 2, ..., 20} 으로 바꿈
    
    empty 
    - S를 공집합으로 바꿈

    1 ≤ M ≤ 3,000,000 => 연산의 수
    1 ≤ x ≤ 20

*/
/*
    Set쓰면 될듯
    - 풀고나니 비트마스킹 문제
    - boolean형 배열(20)을 두고 푸는 방식을 토대로 함
    - int형 자료형 a를 선언하면, 4바이트(4*8bit)를 메모리에 할당 받아
    총 32개에 대해 참, 거짓을 판단 가능
    https://dragon-h.tistory.com/28

    #제출
    1. 성공
*/

fun main(){
    Solution11723().solve()
}
class Solution11723 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val set = mutableSetOf<Int>()
        repeat(br.readLine().toInt()) {
            br.readLine().split(' ').let {
                when (it[0]) {
                    "add" -> set.add(it[1].toInt())
                    "remove" -> set.remove(it[1].toInt())
                    "check" -> bw.write("${if(set.contains(it[1].toInt())) "1" else "0" }\n")
                    "toggle" -> if(set.contains(it[1].toInt())) set.remove(it[1].toInt()) else set.add(it[1].toInt())
                    "all" -> {
                        set.clear()
                        repeat(20) { idx ->
                            set.add(idx+1)
                        }
                    }
                    "empty" -> set.clear()
                }
            }
        }
    
        bw.flush()
        bw.close()
        br.close()
    }
}