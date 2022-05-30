package src.backjoon.stepquestion.`17-stack`
//sliver 4
// 17-4
/*
    (), [] 두 종류의 괄호
    각각 짝을 이뤄야만 한다
    입력의 종료조건 .
*/
import java.io.*
private lateinit var stack : Array<Char?>
private var p = -1
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    while(true){
        val str = br.readLine()
        if(str.equals(".")){
            break
        }
        if(checkVpsLine(str)){
            bw.write("yes\n")
        }else{
            bw.write("no\n")
        }
    }
   
    bw.flush()
    bw.close()
    br.close()
}

private fun checkVpsLine(str : String) : Boolean {
    stack = Array(str.length) { null }
    p = -1

    str.forEach { 
        when(it){
            '(' -> push(it)
            '[' -> push(it)

            ')' -> {
                if(isEmpty() || peek() != '('){
                    return false
                }else{
                    pop()
                }
            }
            ']' -> {
                if(isEmpty() || peek() != '['){
                    return false
                }else{
                    pop()
                }
            }
        }
    }
    return isEmpty()
}

private fun push(c : Char){
    stack[++p] = c
}
private fun pop() : Char? {
    if(p <0){
        return null
    }
    return stack[p--]
}
private fun peek() : Char? {
    if(p <0){
        return null
    }
    return stack[p]
}
private fun isEmpty() : Boolean {
    return p <0
}