// sliver 4
// 17-1

/*
    Int 스택 구현
    명령어 push, pop, size, empty, top
    명령어 + value 

    1<= size <= 10000 명력의 수

    나중에 push된게 먼저 pop 되는 자료구조
*/
import java.io.*
import java.util.StringTokenizer
private lateinit var stack : Array<Int>
private var p = -1
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st : StringTokenizer

    val size = br.readLine().toInt()
    stack = Array(size){-1}

    for(i in 0 until size){
        st = StringTokenizer(br.readLine())
        val operation = st.nextToken()
        when(operation){
            "push" -> push(st.nextToken().toInt())
            "pop" -> bw.write("${pop()}\n")
            "size" -> bw.write("${size()}\n")
            "empty" -> bw.write("${empty()}\n")
            "top" -> bw.write("${peek()}\n")
        }
    }

    bw.flush()
    bw.close()
    br.close()
}

private fun push(n : Int) {
    stack[++p] = n
}
private fun pop() : Int {
    if(p < 0){
        return -1
    }
    return stack[p--]
}
private fun size() : Int {
    if(p < 0){
        return 0
    }
    return p+1
}
private fun empty() : Int {
    if(p < 0){
        return 1
    }
    return 0
}
private fun peek() : Int { // top
    if(p < 0){
        return -1
    }
    return stack[p]
}