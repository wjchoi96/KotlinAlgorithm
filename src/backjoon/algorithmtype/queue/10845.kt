/*
    바킹독님 알고리즘 강의 중 큐 단원 연습문제
    sliver 4

    push 
    pop : 없다면 -1
    size 
    empty : 비어있다면1, 아니면 0
    front : 없다면 -1
    back : 없다면 -1

    1<= n(명령의수) <= 10000
*/
/*
    제출
    1. 틀렸습니다

    2. 틀렸습니다
    - back 조건중 rearP == 0 이 아닌 rearP == FrontP 로 변경
    - 스스로 반례 찾는 연습중

    3. 성공

*/
fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   
    val n = br.readLine().toInt()
    var queue = Queue(n+1)
    
    repeat(n) {
        val op = br.readLine().split(' ')
        when(op[0]){
            "push" -> queue.offer(op[1].toInt())
            "pop" -> bw.write("${queue.poll()}\n")
            "size" -> bw.write("${queue.size}\n")
            "empty" -> bw.write("${queue.empty}\n")
            "front" -> bw.write("${queue.front}\n")
            "back" -> bw.write("${queue.back}\n")
        }
    }

    br.close()
    bw.flush()
    bw.close()
}

private class Queue(
    private var queueSize: Int
) {
    private val queue : Array<Int?> = Array(queueSize){null}
    private var rearP = 0
    private var frontP = 0
    var size = 0
    val empty: Int 
        get() = if(size==0)1 else 0
    val front: Int 
        get() = if(queue[frontP] == null)-1 else queue[frontP]!!
    val back: Int 
        get() {
            return if(rearP == frontP) 
                -1
            else if(queue[rearP-1] == null)
                -1 
            else 
                queue[rearP-1]!!
        }

    
    fun offer(x: Int){
        queue[rearP++] = x
        size++
    }
    fun poll(): Int {
        return if(front==-1)
            -1 
        else{
            size--
            queue[frontP++]!!
        } 
    }
    
}