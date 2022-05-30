//15-11
//sliver 2

/*
    수열 a 가 주어졌을때 가잔 긴 증가하는 부분 수열?
    A = {10, 20, 10, 30, 20, 50}
    => {10, 20, 30, 50} 길이는 4

    증가하는 수열이기만 하면 되는거구만
    => 가장 긴 길이

    1<= n <= 1000
    1<= 수열을 이루는 수 <= 1000

    n 이 2일때
    n-1 과 자기자신을 비교, 더 작은쪽

    n 이 3일때
    
    아 이거도 애매하네
    자기 자신이 포함 안될수도 있네

    n : 1
    10

    n : 2
    10, 20

    n : 3
    10, 20 
    if(arr[n-1] > arr[n])
    => dp[n-1]
    else
    => dp[n] = dp[n-1] + 1

    10, 20, 10, 10, 30, 20, 50
    10
    10, 20
    10

    10, 20, 10, 30, 22, 23, 24
    10
    10, 20
    10, 20
    10, 20 ,30

    22 일때 30, 10 항목을 비교해야한다


    1. 이전 숫자가 현재 숫자보다 큰거나 같은경우
    -> n-2 숫자와 비교한다
    -> n-2 숫자가 현재 숫자보다 작다면 -> dp[n-2] + 1
    -> n-2 숫자도 현재숫자보다 크거나 같다면 -> dp[n-1]+1

    2. 이전 숫자가 현재 숫자보다 작은경우
    -> dp[n-1] + 1
    
*/

import java.io.*
import java.util.StringTokenizer
lateinit var numArr : Array<Int>
lateinit var numDpArr : Array<Int>
fun main(arg : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val size = br.readLine().toInt()

    numArr = Array(size+1){0}
    numDpArr = Array(size+1){-1}

    val st = StringTokenizer(br.readLine())
    for(i in 1 until size + 1){
        numArr[i] = st.nextToken().toInt()
    }
    
    initNumDpArr()

    var max = Int.MIN_VALUE
    for(i in 1 until size+1){
        max = Math.max(max, getNumDp(i))
    }
    bw.write("$max\n")

    br.close()
    bw.flush()
    bw.close()
}
/*
    10, 20, 10 ,30

    dp[1] = 1
    dp[2] = 2

*/
private fun initNumDpArr(){
    numDpArr[0] = numArr[0]
    numDpArr[1] = 1
}

private fun getNumDp(n : Int) : Int {
    if(numDpArr[n] >= 0){
        print("numDpArr[$n] : ${numDpArr[n]}\n")
        return numDpArr[n]
    }
    numDpArr[n] = 1 // 나보다 작은값이 하나도 없는 경우 -1로 수치가 설정되면 정답에 문제가 생긴다
    for(i in n downTo 1){
        // 현재값보다 작은 값을 발견한다면
        if(numArr[i] < numArr[n]){
            // 현재 dp[n] 과 해당마지막 수열 + 1 더한것중 최대값 구한다
            // 어느쪽 수열에 이었을때 더 길게 이어지는지 구하는것
            numDpArr[n] = Math.max(numDpArr[n], getNumDp(i) + 1)
        }
    }
    print("numDpArr[$n] : ${numDpArr[n]}\n")
    return numDpArr[n]
}

/*
    10 20 10 30 20 50 => 4

    10 10 10 20 10 13 14 18 

5
10 20 10 13 14 
=> 4번째 13을 비교할때 10보다 커서 그냥 포함시켜버린다 
-> 10은 수열에 포함되지 않았는데

    n-1 이 수열에 포함되었는지, 안되었는지 판단을 어떻게해야하지

    1. n-2, n-1 을 비교
    => n-2 가 더 크거나 같다면 n-1은 수열에 포함되어있지 않다
    n-2, n 을 비교

    => 이전값들 싹다 비교
    n 값보다 작은걸 발견하면 dp[n] = dp[x] + 1

    => n-1 이 더 크다면 n-1 은 수열에 포함되어있다
*/

/*
    10, 20 
*/