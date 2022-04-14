//sliver 4
//18-1
/*
    정수를 저장하는 queue 구현

    push
    pop : 값이없다면 -1
    size
    empty : 비어있다면1, 아니면 0
    front : 가장 앞의 값, 없다면 -1
    back : 가장 뒤의 값, 없다면 -1

    
    frontP : 가장 앞의 원소 => pop 당할 대상
    backP : 가장 나중에 추가된 원소


 */
import java.io.*
import java.util.StringTokenizer
private lateinit var queue : Array<Int>
private var frontP = -1 
private var backP = -1
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val size = br.readLine().toInt()
    queue = Array(size){-1}
    repeat(size){
        val st = StringTokenizer(br.readLine())
        when(st.nextToken()){
            "push" -> push(st.nextToken().toInt())
            "pop" -> bw.write("${pop()}\n")
            "size" -> bw.write("${size()}\n")
            "empty" -> bw.write("${empty()}\n")
            "front" -> bw.write("${front()}\n")
            "back" -> bw.write("${back()}\n")
        }
    }

    bw.flush()
    bw.close()
    br.close()
}

private fun push(n : Int) {
    queue[++backP] = n
    if(frontP == -1){
        frontP = backP
    }
}

private fun pop() : Int {
    if(frontP < 0 || frontP > backP){
        frontP = -1
        backP = -1
        return -1
    }
    return queue[frontP++]
}

private fun size() : Int {
    return if(frontP < 0){
        0
    }else{
        backP - frontP + 1
    }
}

private fun empty() : Int{
    return if(size() <= 0){
        1
    }else{
        0
    }
}

private fun front() : Int {
    if(frontP < 0 || frontP > backP){
        return -1
    }
    return queue[frontP]
}

private fun back() : Int {
    if(backP < 0 || frontP > backP){
        return -1
    }
    return queue[backP]
}