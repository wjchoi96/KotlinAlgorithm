//sliver1
/*
    연산자 끼워넣기
    네이버 카페 문제50선 중 백트래킹, 완전탐색 유형

    +, -, *, / 연산 4가지가 존재

    N개의 수로 이루어진 수열과, N-1개의 연산자 배열이 주어진다
    연산자 우선순위를 무시하고 앞에서부터 연산을 진행
    나눗셈
    - 정수 나눗셈만 몫으로 취한다
    - 음수를 나눌떄는 양수로 바꾼뒤 몫을 취하고, 몫을 음수로 변환

    만들수있는 식의 결과의 최대와 최소를 구하라

    수열의 순서는 고정  
    연산자의 순서를 바꾸는것이니 연산자를 백트래킹해가며 식을 앞에서부터 완성시켜 나간다
*/
/*
    제출
    1. 성공
    - 이전에 한번 풀어본 문제라그런지, 백트래킹이 익숙해져서 그런지 풀이 고민부터 구현, 디버깅까지 잘 진행되었다
*/
import java.io.*
private var n : Int = 0
private lateinit var numbers : Array<Int>
private lateinit var operators : Array<Int> 
private var max = Int.MIN_VALUE
private var min = Int.MAX_VALUE
private const val plus = 0
private const val minus = 1
private const val multiple = 2
private const val divide = 3
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    n = br.readLine().toInt()
    numbers = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
    operators = br.readLine().split(' ').map{it.toInt()}.toTypedArray()

    dfs(res=numbers[0])
    bw.write("$max\n$min\n")

    bw.flush()
    bw.close()
    br.close()
}

// res 초기값은 number[0] 이다
private fun dfs(depth : Int = 1, res : Int){
    if(depth == n){
        max = Math.max(max, res)
        min = Math.min(min, res)
        print("res : $res, max : $max, min : $min\n")
        return 
    }
    // 연산자 백트래킹을 진행하는것
    for(i in 0 until 4){
        if(operators[i] != 0){
            val value = when(i){
                plus -> res + numbers[depth]
                minus -> res - numbers[depth]
                multiple -> res * numbers[depth]
                divide -> divide(res, numbers[depth])
                else -> continue
            }
            operators[i]--
            dfs(depth + 1, value)
            operators[i]++
        }
    }
}

private fun divide(x : Int, y : Int) : Int {
    return if(x<0) -(-x/y) else x/y
}