/*
    백준 2792
    백준 이분탐색 유형 문제
    https://www.acmicpc.net/problem/2792

    sliver 1

    보석은 M가지 서로 다른 색상 중 한 색상
    모든 보석을 N명의 학생들에게 나눠주려 함
    - 보석을 받지 못하는 학생이 있어도 됨
    - 학생은 항상 같은 색상의 보석만 가져감

    질투심
    - 한 아이가 너무 많은 보석을 가져가면, 다른 아이들이 질투함
    - 가장 많은 보석을 가져간 학생이 가지고 있는 보석의 개수로 수치화 가능
    - 질투심이 최소가 되게 보석을 나누어 주려 함

    예) 빨간보석 4개(RRRR), 파란 보석 7개(BBBBBBB), 5명의 아이
    RR, RR, BB, BB, BBB로 보석을 나누어주면 질투심은 3이되며, 이것이 최소값

    질투심이 최소가 되게 보석을 나누어 주는 방법을 알아내시오

    1 ≤ N ≤ 10^9
    1 ≤ M ≤ 300,000
    M ≤ N
*/
/*
    보석을 가장 많이 가진 아이의 보석의 수를 최소화 해야 함
    질투심이 K일때, k개 이하로 보석을 나눠줄 수 있는가?

    보석을 받지 않아도 되는 아이가 있음
    보석을 색상별로 그룹화
    각 그룹을 k로 나눈값 + 나머지가 있다면 +1 한 값을 count에 더함
    count가 n을 넘었다면 나눠줄 수 없음. k를 높여야함
    n을 넘지않았다면, k를 낮춰봄
*/
/*
    제출
    1. 성공
*/

fun main(){
    Solution2792().solve()
}
class Solution2792 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (n, m) = br.readLine().split(' ').map{ it.toInt() }
        val jewels = Array(m){ 0 }

        var max = Int.MIN_VALUE
        repeat(m) { 
            jewels[it] = br.readLine().toInt() 
            max = Math.max(max, jewels[it])
        } 

        var start = 1
        var end = max
        while(start < end){
            val mid = (start + end) / 2
            val count = shareJewels(jewels, mid)
            when {
                count > n -> start = mid + 1
                else -> end = mid
            }
        }
        bw.write("$start\n")

    
        bw.flush()
        bw.close()
        br.close()
    }

    private fun shareJewels(jewels: Array<Int>, k: Int): Int {
        var count = 0
        for(jewel in jewels){
            count += jewel/k
            if(jewel%k != 0){
                count ++
            }
        }
        return count
    }
}