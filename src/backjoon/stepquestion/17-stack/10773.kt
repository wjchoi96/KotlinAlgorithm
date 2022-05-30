//sliver 4
//17-2

/*
    수를 순서대로 입력
    0을 입력하면 최근에 입력받은 숫자 제거

    입력된 모든 수의 합을 구한다
*/

import java.io.*
private lateinit var stack : Array<Int>
private var p = -1

fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val size = br.readLine().toInt()
    stack = Array(size){0}
    repeat(size) { 
        val value = br.readLine().toInt()
        if(value == 0){
            pop()
        }else{
            push(value)
        }
    }

    var sum = 0
    while(!empty()){
        sum += pop()
    }
    bw.write("$sum\n")
   
    bw.flush()
    bw.close()
    br.close()
}

private fun push(n : Int){
    stack[++p] = n
}
private fun pop(): Int {
    if(p < 0){
        return -1
    }
    return stack[p--]
}
private fun empty(): Boolean {
    return p < 0
}