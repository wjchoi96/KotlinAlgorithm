/*
    바킹독님 알고리즘 강의 중 이분탐색 연습문제 
    https://www.acmicpc.net/problem/10816

    sliver 4

    숫자 카드 2

    정수 하나가 적혀져 있는 카드
    숫자 카드 n개를 가지고 있을 때, 정수 m개가 주어진다면 이 수가 적혀있는 숫자카드를 몇개 가지고 있는지 공백으로 구분하여 출력

    1초
    1<= N <= 500000
    -10000000 <= n[i] <= 10000000

    1<= M <= 500000
    -10000000 <= m[i] <= 10000000
*/
/*
    수 n을 arr에 insert 할때 오름차순을 유지할 수 있는 가장 왼쪽위치와 가장 오른쪽 위치를 찾아 그 차를 구하면 횟수를 구할 수 있다
*/
/*
    가장 왼쪽 위치를 찾는다
    가장 왼쪽위치란 
    - 배열안에 n이 처음 등장하는 위치일 수도 있고
    - n이 없다면 n보다 큰 수가 최초로 등장하는 위치

    start = 0
    end = size (insert 할 가장 오른쪽 위치가 마지막idx 다음일 수 있기 때문)
    mid = (start+end)/2

    arr[mid] > search
    => end = mid
    => 현재 mid 위치도 search 가 추가될 수 있는 가장 왼쪽 위치가 될 수 있기 때문(n이 없다면 n보다 큰 수가 최초로 등장하는 위치)

    arr[mid] < search
    => start = mid+1
    => arr[mid] 가 search 보다 작기 때문에 arr[mid]위치는 search 값이 insert 될 수도 없고, search 가 추가될 수 있는 가장 왼쪽 위치는 더더욱 될 수 없다

    arr[mid] == search
    => end = mid
    => arr[mid] 가 search 와 같기 때문에 배열안에 n이 처음 등장하는 위치가 될 수 있다 => 가장 왼쪽 위치

    === 정리하면
    arr[mid] >= search
    => end = mid

    arr[mid] < search
    => start = mid+1

    종료조건
    start == end 가 되는 순간 가능한 후보가 1개가 되는것 => 정답
*/
/*
    가장 오른쪽 위치 찾기

    가장 오른쪽 위치란
    - n보다 큰 수가 등장하는 첫번째 위치

    start = 0
    end = size (insert 할 가장 오른쪽 위치가 마지막idx 다음일 수 있기 때문)
    mid = (start+end)/2

    arr[mid] > search
    => end = mid

    arr[mid] < search
    => start = mid+1

    arr[mid] == search
    => start = mid+1

    == 정리
    arr[mid] <= search
    => start = mid+1

    arr[mid] > search
    => end = mid

    == 종료조건
    start == end
*/
/*
    제출
    1. 성공

    2. 시간초과: solve2
    - getLeftIdx, getRightIdx 를 사용했지만 시간초과 발생

    map 을 사용하여 횟수를 기록하는 방식,
    카운팅 소트 방식 등이 존재하는 것 같다

*/
fun main(args: Array<String>){
    Solution10816().solve()
}
class Solution10816 {
    private lateinit var arr: Array<Int>
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        br.readLine().toInt() // n not use
        arr = br.readLine().split(' ').map{it.toInt()}.toTypedArray().apply { sort() }
        br.readLine().toInt() // m not use
        br.readLine().split(' ').map{it.toInt()}.forEach {
            bw.write("${getRightIdx(it) - getLeftIdx(it)}\n")
        }
    
        bw.flush()
        bw.close()
        br.close()
    }
    private fun getLeftIdx(v: Int): Int{
        var start = 0
        var end = arr.size
        while(start != end){
            val mid = (start+end)/2
            when {
                arr[mid] >= v -> end = mid
                arr[mid] < v -> start = mid+1
            }
        }
        return start
    }
    private fun getRightIdx(v: Int): Int{
        var start = 0
        var end = arr.size
        while(start != end){
            val mid = (start+end)/2
            when {
                arr[mid] > v -> end = mid
                arr[mid] <= v -> start = mid+1
            }
        }
        return start
    }

    //  getLeftIdx, getRightIdx 를 사용했지만 시간초과 발생
    fun solve2(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        br.readLine().toInt() // n not use
        arr = br.readLine().split(' ').map{it.toInt()}.toTypedArray().apply { sort() }
        br.readLine().toInt() // m not use
        br.readLine().split(' ').map{it.toInt()}.forEach { v ->
            val left = arr.indexOfFirst { it==v } 
            var right = arr.indexOfLast { it==v }.let { 
                if(it != -1) it+1 else it
            } 
            bw.write("${right-left}\n")                                              
        }
    
        bw.flush()
        bw.close()
        br.close()
    }
}