/*
    백준 1920
    바킹독님 알고리즘 강의 중 이분탐색 연습문제

    sliver 4

    N개의 정수가 존재할때 이 안에 X라는 정수가 존재하는지 알아내라

    1초
    1<= N <= 100000
    -2^21 <= 정수의 범위 <= 2^31

    존재하면 1을 없다면 0을 출력
*/
/*
    1. 정렬
    2. 이분탐색
    3. 탐색 종료시까지 발견 못한다면 0
*/

/*
    제출
    1. 성공
*/

fun main(args: Array<String>){
    Solution1920().solve()
}
class Solution1920 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine()
        val arr = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
        arr.sort()

        val m = br.readLine()
        var searchArr = br.readLine().split(' ').map{it.toInt()}

        println("arr: ${arr.toList()}")
        for(searchValue in searchArr){
            bw.write("${binarySearch(searchValue, arr)}\n")
        }
    
        bw.flush()
        bw.close()
        br.close()
    }
    private fun binarySearch(v: Int, arr: Array<Int>): Int{
        println("search $v")
        var start = 0
        var end = arr.size-1
        var mid = (start+end)/2
        while(start<=end){
            println("start[$start], end[$end], mid[$mid]")
            when {
                arr[mid] > v -> {
                    end = mid-1
                    mid = (start+end)/2
                }
                arr[mid] < v -> {
                    start = mid+1
                    mid = (start+end)/2
                }
                arr[mid] == v -> return 1
            }   
        }
        return 0
    }
}
/*
    1, 2, 3, 5, 6 -> 4를 찾는다
    start 0
    end 4
    mid 2

    start 2
    end 4
    mid 3

    start 2
    end 3
    mid 2
*/