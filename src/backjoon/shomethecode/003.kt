/*
    쇼미더코드 C번

    누적합 배열 생성해놓고
    투포인터로 비교 => 이게 틀림

    완탐 해야하나? => 시간초과 날텐데

    

    모두 양의 정수
    => 
*/
/*
    제출
    1. 틀렸습니다(2%)
    - 투포인터 안될거같아서 완탐으로 변경

    2. 시간초과(2%)
    - 날 것 같더라

    

*/

fun main(args: Array<String>){
    SolutionShowMeTheCodeC().solve()
}
class SolutionShowMeTheCodeC {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        val arrA = Array(n){0}
        val arrB = Array(n){0}
        br.readLine().split(' ').map{it.toInt()}.forEachIndexed { i, v ->
            if(i==0) 
                arrA[i] = v
            else
                arrA[i] = arrA[i-1]+v
        }
        br.readLine().split(' ').map{it.toInt()}.forEachIndexed { i, v ->
            if(i==0) 
                arrB[i] = v
            else
                arrB[i] = arrB[i-1]+v
        }
        
        println("${arrA.toList()}")
        println("${arrB.toList()}")

        var start = 0
        var end = 0
        var count = 0

        fun compareArrs(start: Int, end: Int): Boolean {
            return if(start == 0){
                arrA[end] == arrB[end]
            }else{
                arrA[end] - arrA[start-1] == arrB[end] - arrB[start-1]
            }
        }

        for(i in 0 until n){
            for(j in i until n){
                if(compareArrs(i, j)) {
                    count++
                }
            }
        }
        bw.write("$count\n")

    
        bw.flush()
        bw.close()
        br.close()
    }
}
/*
$A = \{1, 2, 3\}$, $B = \{1, 3, 2\}$로, 조건을 만족하는 $i, j$ 쌍은 $(1, 1), (2, 3), (1, 3)$의 세 가지이다.
[0,0]
[1,2]
[0,2]

1 5 2 7 4
5 1 7 2 4

[0,1]

*/