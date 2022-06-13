

import java.util.Arrays/*
    바킹독님 알고리즘 강의 중 이분탐색 연습문제
    gold 4

    세 수의 합

    N 개의 자연수로 이루어진 집합 U
    이 중 세 수를 골랐을 때, 세 수의 합 d도 U안에 포함되는 경우가 있을 수 있다
    이러한 경우 중, 가장 큰 d를 찾아라

    1초
    5<= N <= 1000
*/
/*
    N = 1000이면 O(N^2)이나 O(N^2lgN) 정도의 시간복잡도가 통과하겠다는 짐작 가능하다고 하신다
    
    집합을 정렬한다면 확인 할 경우의 수가 대폭 줄어든다
    합이 최대값을 넘어서는 안되기 때문

    O(N^4)는 4중 for문
    브루트 포스로 모든 경우의 수를 확인하고, 그 결과가 집합에 존재하는지 for문으로 순회하며 확인하는 코드

    O(N^3lgN)
    3중 포문 => 브루트 포스로 모든 경우의 수를 확인하고 해당 결과가 집합에 존재하는지 이분탐색으로 확인

    O(N^2lgN)
    집합의 원소 2개를 합한 경우의 수를 저장해 놓은 two 배열 생성 -> O(N^2)
    two 배열의 원소와 집합U의 원소의 합이 U에 존재하는지 이분탐색
    => 2중 포문 * 이분탐색
    => O(N^2lgN)
    => 근데 이렇게하면 two 배열의 길이가 n^2이니 2중 포문이지만 n^3이 되게 된다
    =========== 다르게 생각하면 =============
    집합U에서 집합U의 원소들끼리 차를 구하며(2중 for문) 그 결과가 two 배열에 존재하는지 이분탐색
    => 2중 포문 * 이분탐색
    => O(N^2lgN)

    (이런 발상을 어떻게 하는거지)
*/
/*
    문제점
    1. 어느정도 시간복잡도 내에 가능한지 계산을 못함
    - 시간복잡도 계산을 꾸준히 해본다
    2. 가능한 시간복잡도 내의 풀이를 생각하지 못함
*/
/*
    제출
    1. 틀렸습니다(6%)

    2. 틀렸습니다(6%)
    - twoArr sort를 안함
    - sort 가 필요없게 twoArr 생성 과정을 변경하던가 sort하던가 하면 될듯

    3. 성공
    - 풀이 자체는 바킹독님 해설을 보며 진행
    - 풀긴 풀었다면, 시간복잡도 계산이나 접근 방식 생각해 내는 등에 대해 부족함을 느낀다
*/
fun main(args: Array<String>){
    Solution2295().solve()
}
class Solution2295 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        val arr = Array(n){0}
        val twoArr = Array(n*n){0}
        repeat(n){arr[it] = br.readLine().toInt()}
        arr.sort()

        var idx = 0
        for(i in 0 until n){
            for(j in 0 until n){
                twoArr[idx++] = arr[i]+arr[j]
            }
        }
        twoArr.sort()

        println("${twoArr.toList()}")
        loop@for(i in n-1 downTo 0){
            for(j in 0 until i+1){
                if(twoArr.binarySearch(arr[i]-arr[j])>=0){
                    bw.write("${arr[i]}\n")
                    break@loop
                }
            }
        }
    
        bw.flush()
        bw.close()
        br.close()
    }
    private fun Array<Int>.binarySearch(n: Int): Int {
        var start = 0
        var end = this.size-1
        while(start<=end){
            val mid = (start+end)/2
            when {
                this[mid]==n -> return mid
                this[mid]<n -> start = mid+1
                this[mid]>n -> end = mid-1
            }
        }
        return -1
    }
}