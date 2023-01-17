/*
    백준 2776
    백준 이분탐색 유형 문제
    https://www.acmicpc.net/problem/2776

    sliver 4
    
    수접2에 적혀있는 숫자들을 순서대로 수접1에 있다면 1을 없다면 0을 출력

    1 ≤ N ≤ 1,000,000
    1 ≤ M ≤ 1,000,000
*/
/*
    제출
    1. 성공
*/

fun main(){
    Solution2776().solve()
}
class Solution2776 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val tc = br.readLine().toInt()

        repeat(tc) {
            br.readLine()
            val arrA = br.readLine().split(' ').map{ it.toInt() }.sorted().toTypedArray()
            br.readLine()
            val arrB = br.readLine().split(' ').map{ it.toInt() }.toTypedArray()

            arrB.forEach {
                var start = 0
                var end = arrA.size
                var res = 0
                while(start<end){
                    val mid = (start + end) / 2
                    when {
                        arrA[mid] > it -> end = mid
                        arrA[mid] == it -> {
                            res = 1
                            break
                        }
                        else -> start = mid + 1
                    }
                }
                bw.write("$res\n")
            }
        }
    
        bw.flush()
        bw.close()
        br.close()
    }
}