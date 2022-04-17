//sliver4
//18-5
/*
    Deck

    원형큐 구현해서 해야겠는데
    front, lear

    push
    queue[++lear] = newValue

    lear+1 == max-1
    lear = 0

    lear+1 == front
    꽉참

    poll
    return queue[front--]

    front == -1
    return false

    front-1 == lear
    poll 은 하고
    front = 

    offer
    pollLast

    offerFirst
    poll
    

    이 문제에서의 앞쪽은 poll 하는 방향이 앞이다
*/
import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val size = br.readLine().toInt()
    val queue = RingQueue(size)
    repeat(size){
        val st = StringTokenizer(br.readLine())
        when(st.nextToken()){
            "push_front" -> queue.offerLast(st.nextToken().toInt())
            "push_back" -> queue.offer(st.nextToken().toInt())
            "pop_front" -> bw.write("${queue.poll()}\n")
            "pop_back" -> bw.write("${queue.pollFirst()}\n")
            "size" -> bw.write("${queue.size()}\n")
            "empty" -> bw.write("${queue.empty()}\n")
            "front" -> bw.write("${queue.front()}\n")
            "back" -> bw.write("${queue.back()}\n")
        }
    }

    bw.flush()
    bw.close()
    br.close()
}


 

private class RingQueue(
    private val size : Int
) {
    private val queue = Array<Int>(size) { Int.MIN_VALUE }
    private var lear = 0
    private var front = 0
    private var num = 0

    fun offer(n : Int) : Boolean{
        if(num >= size){
            return false
        }
        queue[lear++] = n
        num++
        if(lear == size){
            lear = 0
        }
        return true
    }
    fun offerLast(n : Int) : Boolean{
        if(num >= size){
            return false
        }
        if(front == 0){
            front = size-1
            queue[front] = n
        }else{
            queue[--front] = n
        }
        num++
        return true
    }

    fun poll() : Int {
        if(num <= 0){
            return -1
        }
        val value = queue[front++] // 현재 front 가 가리키고 있는 지점엔 item이 있다
        num--
        if(front == size){
            front = 0
        }
        return value
    }
    fun pollFirst() : Int {
        if(num <= 0){
            return -1
        }
        // 현재 lear 가 가리키고 있는 지점은 item이 없다
        val value = if(lear == 0){
            lear = size-1
            queue[lear]
        }else{
            queue[--lear]
        }
        num--
        return value
    }
    
    fun size() : Int {
        return num
    }
    fun empty() : Int{
        return if(num <= 0){
            1
        }else{
            0
        }
    }

    fun front() : Int { // 현재 front 가 가리키고 있는 지점엔 item이 있다
        if(num <= 0){
            return -1
        }
        return queue[front]
    }
    fun back() : Int {  // 현재 lear 가 가리키고 있는 지점은 item이 없다
        if(num <= 0){
            return -1
        }
        return if(lear == 0){
            queue[size-1]
        }else{
            queue[lear-1]
        }
    }
}
