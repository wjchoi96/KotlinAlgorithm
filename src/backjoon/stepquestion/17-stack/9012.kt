//sliver4
//17-3

/*
    올바른 괄호 문자열 찾기 -> vps 라 칭한다
    () -> o
    (()) -> o
    (())) -> x
    (() -> x
    ( -> 를 만나면 숫자 카운팅
    해당 카운팅 만큼 ) 를 만나면 vps 
    아니라면 no

    1. 닫는 괄호가 나왔는데 checkCount <= 0 이라면 no
    2. 열린괄호가 나왔

    ============= 개선 =============
    1. 열린 괄호가 나오면 push
    2. 닫힌 괄호가 나오면 pop
     - pop 해야하는데 stack 이 비어있으면 not vps
    문자열이 끝났는데 stack 이 비어있지않다면 not vps
*/


import java.io.*
private lateinit var stack : Array<Char?>
private var p = -1
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val testCase = br.readLine().toInt()
    repeat(testCase) { 
        val st = br.readLine()
        if(checkVps(st)){
            bw.write("YES\n")
        }else{
            bw.write("NO\n")
        }
    }
    
    bw.flush()
    bw.close()
    br.close()
}
private fun checkVps(st : String) : Boolean {
    stack = Array(st.length){null}
    p = -1

    st.forEach { 
        when(it) {
            '(' -> push(it)
            ')' -> {
                if(empty()) {
                    return false
                } else {
                    pop()
                }
            }
        }
     }
     return empty()
}

private fun push(c : Char){
    stack[++p] = c
}
private fun pop() : Char? {
    if(p<0){
        return null
    }
    return stack[p--]
}
private fun empty() : Boolean {
    return p<0
}

/*
최적화 이전 코드
=> 사실상 stack 을 사용할 이유가 없는 코드였다

import java.io.*
private lateinit var stack : Array<Char?>
private var p = -1
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val testCase = br.readLine().toInt()
    repeat(testCase) { 
        val str = br.readLine()
        stack = Array(str.length){null}
        p = -1
        for(c in str){
            push(c)
        }
        var checkCount = 0
        while(!empty()){
            val value = pop()
            when(value){
                ')' -> {
                    checkCount++
                }
                '(' -> {
                    if(checkCount <= 0){
                        checkCount = -1
                        break
                    }else{
                        checkCount-- 
                    }
                }
            }
        }
        if(checkCount == 0){
            bw.write("YES\n")
        }else{
            bw.write("NO\n")
        }
     }
    


    bw.flush()
    bw.close()
    br.close()
}
private fun push(c : Char){
    stack[++p] = c
}
private fun pop() : Char? {
    if(p<0){
        return null
    }
    return stack[p--]
}
private fun empty() : Boolean {
    return p<0
}

*/
