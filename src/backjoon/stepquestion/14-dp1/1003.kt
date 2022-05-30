package src.backjoon.stepquestion.`14-dp1`
// 15-1
// sliver3
import java.io.*
private var arr : Array<Array<Int>> = Array(41){Array(2){-1}}
private var fabonacciArr : Array<Int> = Array(41){-1} // 이전에 푼 방식
fun main(arg : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val size = br.readLine().toInt()
    for(i in 0 until size){
        val resArr = fabonacci(br.readLine().toInt())
        bw.write("${resArr[0]} ${resArr[1]}")
        bw.write("\n")
    }

    br.close()
    bw.flush()
    bw.close()
}

// 계산은 맞는데..
/*
    fabonacci 1 일때 0,1 이 몇번 호출되는지 기억
    fabonacci 2 일때 기억
    ...
    fabonacci 10 일때 0,1 이 몇번 호출되었는지 기억

    알락말락 하네
*/

// 실제 파보나치 결과는 리턴하지않아도된다
// 파보나치 결과를 구하는게 아닌, 1과0의 호출회수를 구하는 문제인걸 인식!! 
private fun fabonacci(n : Int) : Array<Int> {
    if(n==0){
        arr[n][0] = 1
        arr[n][1] = 0
    }else if(n==1){
        arr[n][0] = 0
        arr[n][1] = 1
    }
    // 숫자 n의 파보나치 과정의 0 과 1의 호출 회수가 저장되어있나 확인
    if (arr[n][0] == -1 || arr[n][1] == -1){
        arr[n][0] = fabonacci(n-1)[0] + fabonacci(n-2)[0]
        arr[n][1] = fabonacci(n-1)[1] + fabonacci(n-2)[1]
    }
    return arr[n]
}

// 메모리 초과뜸
// 수정본이여서 맞진않을거
// private fun fabonacci(n : Int) : Int{
//     if(n==0){
//         arr[n][0]++
//         return 0
//     }else if(n==1){
//         arr[n][1]++
//         return 1
//     }
//     if(fabonacciArr[n] < 0){
//         fabonacciArr[n] = fabonacci(n-1) + fabonacci(n-2)
//     }
//     return fabonacciArr[n]
// }