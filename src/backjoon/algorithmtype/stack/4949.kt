/*
    바킹독님 알고리즘 강의 중 스택, 올바르지 않은 괄호 쌍 단원
    
    균형잡힌 세상

    이전에 풀어보고 감도 어느정도 있지만, 한번 더 구현해보자
    (), [] 두가지 종류의 괄호가 존재

    1. 문자열을 순회하며 여는 괄호를 만나면 push
    2. 닫는 괄호를 만나면 pop 
    - 이때 stack 이 비어있다면 false
    - pop 한 괄호와 한쌍이 아니라면 false

    3. 문자열이 끝났을때 스택이 비어있다면 true

    각 줄은 .로 끝난다
*/
/*
    제출
    1. 성공
    - 지난번 제출보다 코드도 줄었네 -> 지난번엔 스택을 구현해서 진행했구나
*/

import java.util.Stack
fun main(args : Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   

    while(true){
        val row = br.readLine()
        if(row == "."){
            break
        }
        val stack = Stack<Char>()
        for(i in 0 until row.count()){
            when(row[i]){
                '(', '[' -> stack.push(row[i])
                ')' -> {
                    if(stack.isEmpty() || stack.pop() != '('){
                        bw.write("no\n")
                        break
                    }
                }
                ']' -> {
                    if(stack.isEmpty() || stack.pop() != '['){
                        bw.write("no\n")
                        break
                    }
                }
                '.' -> {
                    if(stack.isEmpty()){
                        bw.write("yes\n")
                    }else{
                        bw.write("no\n")
                    }
                    break
                }
            }
        }
    }

    bw.flush()
    bw.close()
    br.close()
}