//15-12
//gold 3

/*
    S1 < S2 < ... Sk-1 < Sk > Sk+1 > ... SN-1 > SN 
    => 바이토닉 수열
    1,2,5,4,3

    1<= n <= 1000 
    1<= 수열의 원소 <= 1000

    arr[n] 을 기준으로 왼쪽, 오른쪽으로 수열을 나누어서
    각각 arr[n]이 포함되는 수열 생성
    
    dp[front][n] / dp[back][n] 에 각각 저장해서
    두 길이가 같다면 바이토닉 수열 => x
    => 그냥 올라갔다가 내려오면 바이토닉 수열이였네 ㅅㅂ
    두 길이의 합 -1 이 총 길이

    1 5 2
    
    // 참조 : https://st-lab.tistory.com/136
    // 접근 방식은 잘 잡았다 => idx를 중심으로 front dp, back dp 합 -1 이 정답이다
    // 다만 바이토닉 수열 자체를 잘못 이해했었다 -> 길이가 대칭이 되어야하는줄알고 front, dp 의 길이도 고려하려 했었다
    // back dp 함수 잘못 만든거 제외하면 얼추 ㄱㅊ았던듯
*/

import java.io.*
import java.util.StringTokenizer
private val front = 0
private val back = 1
lateinit var bitonicDp : Array<Array<Int>> 
lateinit var bitonicArr : Array<Int>

fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    
    val size = br.readLine().toInt()
    bitonicArr = Array(size+1){0}
    bitonicDp = Array(2){ Array(size+1) {-1} }
    val st = StringTokenizer(br.readLine())
    
    for(i in 1 until size+1){
        bitonicArr[i] = st.nextToken().toInt()
    }

    var max = Int.MIN_VALUE
    for(i in 1 until size + 1){
        max = Math.max(getBitonicDp(i, size), max)
    }
    bw.write("$max\n")

    for(i in 1 until size+1){
        bw.write("${bitonicArr[i]}=> front[$i] : ${bitonicDp[front][i]}, back[$i] : ${bitonicDp[back][i]}\n")
    }

    br.close()
    bw.flush()
    bw.close()
}

// n 을 중심으로 한 바이토닉 수열의 길이를 리턴
private fun getBitonicDp(n : Int, size : Int) : Int{
   if(bitonicDp[front][n] >= 0 && bitonicDp[back][n] >= 0){
       return bitonicDp[front][n] + bitonicDp[back][n] - 1
   }
   bitonicDp[front][n] = getFrontDp(n)
   bitonicDp[back][n] = getBackDp(n, size)
   return bitonicDp[front][n] + bitonicDp[back][n] - 1
}
private fun getFrontDp(n : Int) : Int {
    if(bitonicDp[front][n] >= 0){
        return bitonicDp[front][n]
    }
    bitonicDp[front][n] = 1
    for(i in n-1 downTo 1){ // arr[1] ~ arr[n] 
        if(bitonicArr[i] < bitonicArr[n]){
            bitonicDp[front][n] = Math.max(getFrontDp(i) + 1, bitonicDp[front][n])
        }
    }
    return bitonicDp[front][n]
}

private fun getBackDp(n : Int, size : Int) : Int {
    if(bitonicDp[back][n] >= 0){
        return bitonicDp[back][n]
    }
    bitonicDp[back][n] = 1
    for(i in n+1 until size + 1){ // arr[n] ~ arr[size]
         // bitonicArr[n] < bitonicArr[i] 로 했었는데 왜 착각한거지? 
         // 맞네, 어쩄든 이후의 값이 현재의 값보다 작아야 수열에 포함될수 있는거니까
        if(bitonicArr[i] < bitonicArr[n]){
            bitonicDp[back][n] = Math.max(getBackDp(i, size) + 1, bitonicDp[back][n])
        }
    }
    return bitonicDp[back][n]
}

/*
    1 5 2 1 4 3 4 5 2 1
    n = 1
    front : 1
    back : 5,4,2,1
*/