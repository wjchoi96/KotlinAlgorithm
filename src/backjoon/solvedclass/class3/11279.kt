/*
    백준 11279
    solved.ac class3+ 문제
    https://www.acmicpc.net/problem/11279

    silver 2

    최대힙을 사용해 다음 연산을 지원하는 프로그램 작성
    1. 배열에 자연수 x를 넣음
    2. 배열에서 가장 큰 값을 출력, 그 값을 배열에서 제거

    프로그램은 비어있는 배열에서 시작

    1 ≤ N ≤ 100,000 => 연산의 개수
    X가 자연수라면, X를 삽입
    X가 0이라면, 배열에서 가장 큰 값을 출력하고 그 값을 배열에서 제거
    - 배열이 비어있을때, 가장 큰 값을 출력하라 하면 0을 출력
    
    입력되는 자연수는 2^31보다 작음 => int 사용
*/
/*
    최대힙 사용하면 끝
    배열로 최대힙 구현 문제인듯

    #최대힙 사용 풀이
    1. 성공

    #최대힙 구현 풀이
    
*/

import java.util.PriorityQueue
fun main(){
    Solution11279().solve()
}
class Solution11279 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        val maxHeap = PriorityQueue<Int>(compareBy({-it}))

        repeat(n) {
            when (val input = br.readLine().toInt()) {
                0 -> {
                    if(maxHeap.isEmpty()){
                        bw.write("0\n")
                    }else {
                        bw.write("${maxHeap.poll()}\n")
                    }
                }
                else -> maxHeap.offer(input)
            }
        }
    
        bw.flush()
        bw.close()
        br.close()
    }
}