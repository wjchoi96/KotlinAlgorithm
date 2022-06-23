/*
    바킹독님 알고리즘 강의 중 이진 검색 트리 단원 연습문제
    gold 4
    https://www.acmicpc.net/problem/7662

    우선순위 큐 문제이지만, 문제가 괜찮아서 소개
    우선순위 큐를 모르더라도 bst로 풀이 가능
    삽입, 최댓값 삭제, 최솟값 삭제 구현 시 배열로 단순히 구현한다면 O(k^2)가 된다는건 쉽게 감이 올것
    해시를 사용하자니, 해시테이블 안의 원소 중 최갯값, 최소값을 효율적으로 찾을 방법이 없다
    그런데, 이진검색트리가 딱 이 문제의 상황에 적합하다

    이중 우선순위 큐

*/

fun main(args: Array<String>){
    Solution7662().solve()
}
class Solution7662 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
    
        bw.flush()
        bw.close()
        br.close()
    }
}