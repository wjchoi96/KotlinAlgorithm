// sliver2
// 16-12
//2004
/*
    이항계수 (n/k) 의 끝자리 0의 개수를 출력
    점화식
    k == 0 || k == n : => 1
    (n-1/k-1) + (n-1/k)
    0<= k <= n <= 2000000000
 
    getCombZero => (n/k)의 의 끝자리 구하기
    이항계수는 +연산을 통해 진행된다

    점화식이 다르네?
    (n/m) = n! / (n-m)! * m!
    
    n! 애서 사용된 2와 5의 수를 더하고
    (n-m)! * m! 에서 사용된 2와 5의 수를 빼준다
    -> (n-m)! 에서 사용된 2와 5의 개수 + m!에서 사용된 2와 5의 개수
    -> 곱하는과정은 더해주고, 나누는 과정은 빼주는것

    n! 의 2와 5의 개수를 구하는법
    -> n 을 2로 나눠가며 누적합을 구한다
    -> 5 를 5로 나눠가며 누접합을 구한다

    n의 2의 개수 - (n-m)의 2의 개수 - m 의 2의 개수
    n의 5의 개수 - (n-m)의 5의 개수 - m 의 5의 개수

*/


import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))    
    val st = StringTokenizer(br.readLine())

    val temp1 = st.nextToken().toInt()
    val temp2 = st.nextToken().toInt()
    val n = Math.max(temp1, temp2)
    val k = Math.min(temp1, temp2)

    val twoCount = getDivCount(n, 2) - getDivCount(n-k, 2) - getDivCount(k, 2)
    val fiveCount = getDivCount(n, 5) - getDivCount(n-k, 5) - getDivCount(k, 5)
    bw.write("${Math.min(twoCount, fiveCount)}\n")

    bw.flush()
    bw.close()
    br.close()
}

private fun getDivCount(n : Int, div : Int) : Int {
    var count = 0
    var value = n
    while(value>=div){ // 여길 착각했네
        count += value/div
        value /= div
    }
    return count
}

private lateinit var comDp : Array<Array<Int>>
private fun initCombDp(n : Int, k : Int){
    comDp = Array(n+1){Array(k+1){-1}}

    for(ni in 0 until n+1){
        comDp[ni][0] = 1
        for(ki in 0 until k+1){
            if(ni==ki){
                comDp[ni][ki] = 1
            }
        }
    }
}
var logCall = 0
private fun combination(n : Int, k : Int) : Int{
    print("[$n, $k] called[${++logCall}]\n")
    if(comDp[n][k] >= 0){
        return comDp[n][k]
    }
    comDp[n][k] = combination(n-1, k-1) + combination(n-1,k)
    return comDp[n][k]
}

/*
    [4, 2] called[1]
    [3, 1] called[2]
    [2, 0] called[3] => return 1
    [2, 1] called[4]
    [1, 0] called[5] => return 1
    [1, 1] called[6] => return 1
    [3, 2] called[7]
    [2, 1] called[8]
    [2, 2] called[9] => return 1

    (4,2) -> (3,1) + (3,2) => 3 + (3,2) => 3 + 3 => 6
    (3,1) -> (2,0) + (2,1) => 1 + (2,1) => 1 + 2 => 3
    (2,1) -> (1,0) + (1,1) => 1 + 1 => 2

    (3,2) -> (2,1) + (2,2) -> (2,1) + 1 -> 2 + 1 = 3
    (2,1) -> (1,0) + (1,1) -> 1 + 1 => 2
*/