/*
    네이버 카페 문제 50선 중 이분탐색 유형 문제
    sliver 5

    숫자 카드

    숫자 카드는 정수 하나가 적힌 카드이다
    숫자카드 N개 중 정수 M개가 주어졌을때 이 수가 적혀잇는 숫자카드를 가지고 있는 지 아닌지 구하시오

    가지고 있다면1, 아니라면 0을 출력

    2초
    1<= N <= 500,000
    -10,000,000 <= Ni <= 10,000,000
    1<= M < 500,000
*/
/*
    1. 직접 이분탐색 구현
    - 성공
    2. Arrays.binarySearch 사용
    - 성공
    3. contains 사용
    BOJ를 기준으로 binarySearch를 이용한 풀이는 2164ms, contains는 4940ms 시간이 소요되었다.
    contains: 선형탐색
*/

fun main(args: Array<String>){
    Solution10815().solve()
}
class Solution10815 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        val arr = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
        arr.sort()
        val m = br.readLine().toInt()
        val searchItems = br.readLine().split(' ').map{it.toInt()}.toTypedArray()

        repeat(m){
            bw.write("${binarySearch3(searchItems[it], arr)} ")
        }
    
        bw.flush()
        bw.close()
        br.close()
    }
    private fun binarySearch3(item: Int, arr: Array<Int>): Int {
        val res = arr.contins(item)
        return if(res<0) 0 else 1
    }
    private fun binarySearch2(item: Int, arr: Array<Int>): Int {
        val res = arr.binarySearch(item)
        return if(res<0) 0 else 1
    }
    private fun binarySearch(item: Int, arr: Array<Int>): Int{
        var start = 0
        var end = arr.size-1
        while(start<=end){
            val mid = (start+end)/2
            when {
                item>arr[mid] -> start = mid+1
                item==arr[mid] -> return 1
                item<arr[mid] -> end = mid-1
            }
        }
        return 0
    }
}