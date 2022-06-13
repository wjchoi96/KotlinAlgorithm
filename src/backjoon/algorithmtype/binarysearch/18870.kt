/*
    바킹독님 알고리즘 강의 중 이분탐색 단원 연습문제
    sliver 2

    이전에 풀었을때는 map을 사용하여 풀었었다

    수직선 위에 N개의 좌표 X1, X2, X3... XN 이 있다
    좌표압축을 적용하려 한다
    좌표 압축이란 
    Xi 를 좌표 압축 한 결과는 Xi>Xj를 만족하는 서로 다른 좌표의 개수와 같다
    => 정렬 후, Xi가 몇번쨰 위치에 존재하냐를 구하면 된다

    2초
    1<= N <= 1000000
    -10^9 <= Xi <= 10^9

    1. 정렬
    1-1. 중복제거
    2. 이진탐색으로 idx 탐색
*/
/*
    제출
    1. 틀렸습니다
    - 중복 제거 for문 범위 틀림

    2. 성공

    3. 성공
    - 개선안 적용

*/
/*
    개선안
    Set 에다가 push 한 후 중복이 제거된 목록인 Set 을 List 로 변경시켜(O(n))
    sort 이후 binarySearch
    => solve2
*/
import java.util.Stack
fun main(args: Array<String>){
    Solution18870().solve2()
}
class Solution18870 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        val arr = br.readLine().split(' ').map{it.toInt()}
        val stack = Stack<Int>()
        val temp = arr.sorted().toTypedArray()
        println("temp ${temp.toList()}")
        for(i in 0..n-1){
            println("idx $i")
            if(i==0){
                stack.push(temp[i])
            } else if(temp[i-1] != temp[i]){
                println("push")
                stack.push(temp[i])
            }
        }
        println("${stack.toList()}")
        val sortedArr = stack.toList().toTypedArray()
        arr.forEach{
            var res = binarySearch(it, sortedArr)
            if(res<0) res = 0
            bw.write("$res ")
        }

        bw.flush()
        bw.close()
        br.close()
    }
    private fun binarySearch(n: Int, arr: Array<Int>): Int {
        var start = 0
        var end = arr.size-1
        while(start <= end){
            val mid = (start+end)/2
            when{
                arr[mid]<n -> start = mid+1
                arr[mid]>n -> end = mid-1
                arr[mid]==n -> return mid
            }
        }
        return -1
    }

    fun solve2(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        val arr = br.readLine().split(' ').map{it.toInt()}
        val set = arr.toHashSet()
        val sortedArr = set.toList().sorted().toTypedArray()
        
        arr.forEach{
            var res = binarySearch(it, sortedArr)
            if(res<0) res = 0
            bw.write("$res ")
        }

        bw.flush()
        bw.close()
        br.close()
    }
}