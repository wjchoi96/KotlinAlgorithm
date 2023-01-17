/*
    백준 12052
    백준 이분탐색 유형 문제
    https://www.acmicpc.net/problem/12015

    gold 2

    수열 A
    가장 긴 증가하는 부분 수열을 구하라
    
    예)
    A = [10, 20, 10, 30, 20 , 50]
    가장 긴 증가하는 부분 수열은
    A = [10, 20, 30, 50] 이고 길이는 4

    1 ≤ N ≤ 1,000,000
    1 ≤ Ai ≤ 1,000,000
*/
/*
    https://st-lab.tistory.com/285 참고

    수열을 처음부터 탐색하며 lis를 만들어감
    - 현재값보다 큰 값을 추가
    - 현재값보다 작은 값은 적절한 위치를 찾아 대치
        - 해당 값과 같거나 처음으로 큰 값을 찾아 대치시킴
    - 대치를 시키는 이유는, 가장 긴 수열의 길이를 구하는 것이기 때문
*/
/*
    제출
    1. 틀렸습니다(0%)
    - lowerBound에 arr을 매개변수로 전달하던 실수를 해결

    2. 시간초과(0%)
    - lib의 현재 size를 구하기 위한 로직을 제거하고, pointer사용

    3. 성공
    - 접근방식은 블로그를 참고했지만, 구현은 손쉽게 해냄

*/

fun main() {
    Solution12052().solve()
}

class Solution12052 {
    fun solve() {
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
        
        val n = br.readLine().toInt()
        val arr = br.readLine().split(' ').map{ it.toInt() }.toTypedArray()
        val lis = Array(n){ 0 }
        var lisPointer = 0

        lis[lisPointer] = arr[0]
        for(i in 1 until n){
            when {
                lis[lisPointer] < arr[i] -> lis[++lisPointer] = arr[i] // 현재 lis값보다 큰값이라면, lis에 추가
                else -> { // 현재 lis보다 작거나 같다면, lis에 대치(사실 같은경우는 할필요없음)
                    lis[getLowerBoundIdx(arr[i], lis, lisPointer + 1)] = arr[i]
                } 
            }
        }
        println("${lis.toList()}")
        bw.write("${lisPointer + 1}\n")

        bw.flush()
        bw.close()
        br.close()
    }

    private fun getLowerBoundIdx(value: Int, lis: Array<Int>, size: Int): Int {
        var start = 0
        var end = size - 1

        while(start<end){
            val mid = (start + end) / 2
            when {
                value <= lis[mid] -> end = mid
                else -> start = mid + 1
            }
        }
        return start
    }
}