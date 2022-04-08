// 15-9
// sliver 1

/*
    45456 => 인접한 모든 자리의 차이가 1
    이런 수를 계단 수라고 한다( 0으로 시작하는 수는 게단수가 아니다 )
    길이가 n인 계단수가 총 몇개 있는지 구해보자
    정답을 1,000,000,000 으로 나는 수를 출력
    1 <= n <= 100
    
    12121
    12123

    자연수의 n번째 자리값은 오른쪽부터 카운팅

    n번째 자리수의 자리값이 0 -> 다음 자리값은 1
    n번쨰 자리수의 자리값이 9 -> 다음 자리값은 8
    나머지는 n-1 / n+1 가능
*/

// 참고 : https://st-lab.tistory.com/134
// 약간의 초반 접근 방식을 참고하려했다가, 약간의 접근방식만 보고 이해하여 풀었다
// 100000000 로 나눠줘서 long타입 범위를 벗어나지 않게 해주는게 헷갈리네
import java.io.*
// n자리수의 각각의 자릿값(0~9)
lateinit var stairNumDp : Array<Array<Long>>
lateinit var bw : BufferedWriter
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    bw = BufferedWriter(OutputStreamWriter(System.out))

    val size = br.readLine().toInt()
    // (size+1)자리수의 각각의 자릿값(0~9)
    stairNumDp = Array(size+1){Array(10){-1}}

    initStairNumDp()
    var count : Long = 0
    for(i in 1 until 10){
        count += getStairNum(size, i)
    }
    bw.write("${count % 1000000000}\n")

    br.close()
    bw.flush()
    bw.close()
}

private fun initStairNumDp(){
    for(i in 0 until 10){
        stairNumDp[0][i] = 0
        stairNumDp[1][i] = 1
    }
}

// 100자리수까지 있기때문에 각 리턴값을 100000000 로 나눠주지않으면 long타입 값을 벗어날 수 있다
private fun getStairNum(n : Int, num : Int) : Long{
    if(stairNumDp[n][num] >= 0){
        return stairNumDp[n][num] % 1000000000
    }
    stairNumDp[n][num] = if(num == 0){
        getStairNum(n-1, 1) 
    }else if(num == 9){
        getStairNum(n-1, 8) 
    }else{
        getStairNum(n-1, num-1) +  getStairNum(n-1, num+1)
    }
    return stairNumDp[n][num] % 1000000000
}
/*
    길이가 n인 계단수를 구하시오

    n번째 자리수가 1인 숫자의 계단수
    -> n-1번째 자리수가 2인 숫자의 계단수

    n-1번쨰 자리수가 2인 숫자의 계단수
    -> n-2번째 자리수가 3인 숫자의 계단수 + n-2번째 자리수가 1인 숫자의 계단수

    n-2번째 자리수가 3인 숫자의 계단수
    -> n-3번째 자리수가 2인 숫자의 계단수 + n-3번째 자리수가 4인 숫자의 계단수
    
*/