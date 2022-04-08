//sliver2
//16_4

/*
    괄호가 없는 수식에서 괄호를 쳐서 최소값을 내야한다
    0~9, +, - 로 이루어져있다

    수는 0으로 시작 가능, 5자리보다 많이 연속되는 숫자는 없다

    숫자배열 
    연산자배열 
    plus = 0
    minus = 1

    55-50+40
    이거 숫자랑 연산자로 구분해서 입력받아야한다

    괄호를 사용해 최소값을 만들어라 => 연산의 순서를 바꾸어서 최소값을 만들어라
    55-50+40

    덧셈을 먼저 수행
    그 후에 뻴셈을 수행

    operator 가 plus 인 idx를 구한다
    numArr[idx] + numArr[idx+1] 을 수행해서 해당 값들을 다 더한다

    30 - 70 - 20 + 40 - 10 + 100 + 30 - 35
    num = [30, 70, 20, 40, 10, 100, 30, 35]
    operator = [-, -, +, -, +, +, -]

    plus idx => 2, 4, 5
    20+40, 10 + 100, 100 + 30

    // 첫 시도
    // var input = br.readLine()
    // input = input.replace("-", " - ")
    // input = input.replace("+", " + ")
    // val st = StringTokenizer(input)
    // val count = st.countTokens()/2 + 1
    // val numArr = Array<Int>(count){-1}
    // val operatorArr = Array<Int>(count-1){-1}
    // var idx = 0
    // while(st.hasMoreTokens()){
    //     if(idx % 2 == 0){ // 숫자
    //         numArr[idx/2] = st.nextToken().toInt()
    //     }else{
    //         operatorArr[idx/2] = if(st.nextToken().equals("+")){
    //             plus
    //         }else{
    //             minus
    //         }
    //     }
    //     idx++
    // }
*/

import java.io.*
import java.util.StringTokenizer
private val plus = 0
private val minus = 1
fun main(args : Array<String>){ 
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    var sum = Int.MAX_VALUE
    val subSt = StringTokenizer(br.readLine(), "-") // - 기호를 replace 하면서 문자열을 쪼갠다
    while(subSt.hasMoreTokens()){
        var temp = 0
        val subToken = subSt.nextToken()
        // bw.write("m : ${subToken}\n")
        val plusSt = StringTokenizer(subToken, "+")
        while(plusSt.hasMoreTokens()){
            val plusToken = plusSt.nextToken().toInt()
            // bw.write("p : ${plusToken}\n\n")
            temp += plusToken
        }

        if(sum == Int.MAX_VALUE){ // 첫번째 수는 무조건 양수
            sum = temp
        }else{
            sum -= temp
        }
        /*
            처음엔 sum 연산 과정이 이해가 안갔었다
            마이너스를 제외한 토큰들 목록 => 30, 70, 20+40, 10+100+30, 35 등으로 나누어진다
            해당 숫자, 연산들을 모두 진행한다음
            첫번째 수를 제외하고 모두 마이너스 연산을 진행하주면된다
        */
    }
    bw.write("$sum\n")


    br.close()
    bw.flush()
    bw.close()
}