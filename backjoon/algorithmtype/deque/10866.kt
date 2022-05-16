/*
    바킹독님 알고리즘 강의 중 덱 단원 연습문제
    sliver 4

    덱

    empty: 비어있다면 1 아니면 0
    front, back, pop: 없다면 -1
*/
/*
    1<= n <=10000
*/
/*
    지난번엔 원형 덱으로 구현했었네
    제출
    1. 성공 - 선형 덱 구현
    2. 성공 - 덱 콜렉션 사용
*/

import java.util.LinkedList
import java.util.Deque
fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   
    val n = br.readLine().toInt()
    val deque : Deque<Int> = LinkedList()
    
    repeat(n) {
        val op = br.readLine().split(' ')
        when(op[0]){
            "push_back" -> deque.offerLast(op[1].toInt())
            "push_front" -> deque.offerFirst(op[1].toInt())
            "pop_back" -> {
                val poll = deque.pollLast()
                bw.write("${if(poll==null)-1 else poll}\n")
            }
            "pop_front" -> {
                val poll = deque.pollFirst()
                bw.write("${if(poll==null)-1 else poll}\n")
            }
            "size" -> bw.write("${deque.size}\n")
            "empty" -> bw.write("${if(deque.size == 0)1 else 0}\n")
            "front" -> {
                val first = deque.peekFirst()
                bw.write("${if(first==null)-1 else first}\n")
            }
            "back" -> {
                val last = deque.peekLast()
                bw.write("${if(last==null)-1 else last}\n")
            }
        }
    }
    br.close()
    bw.flush()
    bw.close()
}

// solve1 use custom deque
private fun solve1(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   
    val n = br.readLine().toInt()
    var deque = Deque(n+1)
    
    repeat(n) {
        val op = br.readLine().split(' ')
        when(op[0]){
            "push_back" -> deque.pushBack(op[1].toInt())
            "push_front" -> deque.pushFront(op[1].toInt())
            "pop_back" -> bw.write("${deque.popBack()}\n")
            "pop_front" -> bw.write("${deque.popFront()}\n")
            "size" -> bw.write("${deque.size}\n")
            "empty" -> bw.write("${deque.empty}\n")
            "front" -> bw.write("${deque.front}\n")
            "back" -> bw.write("${deque.back}\n")
        }
    }
    br.close()
    bw.flush()
    bw.close()
}

private class Deque(
    private val max: Int
){
    private val deque: Array<Int?> = Array(2*max+1){null}
    private var itemSize = 0
    val size: Int
        get() = itemSize
    val empty: Int
        get() = if(size==0)1 else 0
    /*
        앞, 뒤는 보편적인 기준의 앞, 뒤 기준을 따른다
        head: front 값을 가리킴
        tail: back + 1 값을 가리킴
        front: 앞의값
        back: 뒤의값
    */
    private var head: Int = max
    private var tail: Int = max
    val front: Int
        get() = if(deque[head] == null)-1 else deque[head]!!
    val back: Int
        get() = if(deque[tail-1] == null)-1 else deque[tail-1]!!
    
    fun pushFront(x: Int){
        deque[--head] = x
        itemSize++
    }
    fun pushBack(x: Int){
        deque[tail++] = x
        itemSize++
    }
    fun popFront(): Int {
        val pop = front
        if(pop != -1){
            itemSize--
            deque[head++] = null
        }
        return pop
    }
    fun popBack(): Int {
        val pop = back
        if(pop != -1){
            itemSize--
            deque[--tail] = null
        }
        return pop
    }
}