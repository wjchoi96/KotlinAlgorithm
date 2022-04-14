//sliver3
//17-5

/*
    +
    - 1
    ++
    - 2
    +++
    - 5

    5 다음에 3을 pop 해야하는데
    그 사이에 4가 존재해서 불가능

    => 문제 이해 제대로 못한거 검색글에서 확인
    push 는 무조건 오름차순
    이전에 어디까지 push 했는지 확인 필요
    start 에 저장

 */

import java.io.*
private lateinit var stack : Array<Int>
private var p = -1
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()

    val size = br.readLine().toInt()
    stack = Array(size){0}

    var pushP = 1 // push pointer
    var isSuccess = true

    for(i in 0 until size){
        val n = br.readLine().toInt()
        while(n >= pushP){
            push(pushP++)
            sb.append('+').append('\n')
        }
        val pop = pop() // pop 한 값이 n 과 다르다면 실패
        if(pop == n){
             sb.append('-').append('\n')
        }else{
            bw.write("NO\n")
            isSuccess = false
            break
        }
    }
    if(isSuccess){
        bw.write("$sb\n")
    }

    bw.flush()
    bw.close()
    br.close()
}

private fun push(n : Int) : Boolean {
    if(p == stack.size-1){
        return false
    }
    stack[++p] = n
    return true
}
private fun pop() : Int? {
    if(p < 0){
        return null
    }
    return stack[p--]
}