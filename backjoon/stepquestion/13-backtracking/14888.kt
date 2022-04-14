//sliver1
//14-7

/*
    N개의 수와 n-1개의 연산자가 주어진다

    수의 개수
    수열 구성 값
    덧셈의개수, 뻴셈의개수, 곱셈의개수, 나눗셈의 개수

    참고 : https://st-lab.tistory.com/121
*/


import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st : StringTokenizer
    val size = br.readLine().toInt()
    //idx로 연산자의 종류에 접근, 값으로 연산자의 개수에 접근
    //0 : +, 1 : -, 2 : *, 3 : /
    val operator : Array<Int> = Array(4){0} // 연산자의 배열
    val arr : Array<Int> = Array(size){0}

    st = StringTokenizer(br.readLine())
    for(i in 0 until size){
        arr[i] = st.nextToken().toInt()
    }
    st = StringTokenizer(br.readLine())
    for(i in 0 until 4){
        operator[i] = st.nextToken().toInt()
    }

    dfs(arr[0], 0, operator, arr)
    bw.write("$max\n$min\n")

    bw.flush()
    bw.close()
    br.close()
}

private var max = Int.MIN_VALUE
private var min = Int.MAX_VALUE
private fun dfs(num : Int, depth : Int, operator : Array<Int>, arr : Array<Int>){
    if(depth == arr.size-1){ // 연산을 다 하면 끝
        if(max<num){
            max = num
        }
        if(min>num){
            min = num
        }
        return
    }
    for(i in 0 until 4){
        if(operator[i] != 0){
            operator[i]--
            dfs(operator(i, num, arr[depth+1]), depth+1, operator, arr)
            operator[i]++
        }
    }
}

private fun operator(operator : Int, num : Int, num2 : Int) : Int{
    return when(operator){
        0 -> num + num2
        1 -> num - num2
        2 -> num * num2
        3 -> num / num2
        else -> num
    }
}