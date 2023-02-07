/*
    LinkedList 개념을 이용한 Queue 구현

    #포인트
    1. 자꾸 poll, pop할때 head를 Null로 설정해놓음
    2. poll 하였을때, head와 tail이 같아져야 하는 경우가 존재
    - 빈 queue에 처음 item을 offer했을때 처럼

    #검증문제
    - 백준 10845
    https://www.acmicpc.net/problem/10845
    
    1. 성공
*/

fun main() {
    BaseAlgorithmQueue().solve()
}
class BaseAlgorithmQueue {
    fun solve() {
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val queue = QueueUseLinkedList<Int>()

        repeat(br.readLine().toInt()) {
            val op = br.readLine().split(' ')
            when(op[0]){
                "push" -> queue.offer(op[1].toInt())
                "pop" -> bw.write("${queue.poll() ?: -1}\n")
                "size" -> bw.write("${queue.size()}\n")
                "empty" -> bw.write("${if(queue.empty()) 1 else 0}\n")
                "front" -> bw.write("${queue.front() ?: -1}\n")
                "back" -> bw.write("${queue.back() ?: -1}\n")
            }
        }
    

        bw.flush()
        bw.close()
        br.close()
    }

    class QueueUseLinkedList<T : Any> {
        private var head: Node<T>? = null // poll 될 위치
        private var tail: Node<T>? = null // offer 되는 위치
        private var size = 0

        // 1[HEAD] 2 3 4 5[TAIL]
        // 2[HEAD] 3 4 5[TAIL]
        // 2[HEAD] 3 4 5 6[TAIL]
        fun offer(data: T){
            val node = Node(data)
            when(tail) {
                null -> {
                    tail = node
                    head = node
                }
                else -> {
                    node.prev = tail
                    tail?.next = node
                    tail = node
                }
            }
            size++
        }

        fun poll(): T? {
            head?.next?.prev = null
            val node = head
            head = head?.next
            node?.let { 
                size-- 
                if(size <= 1){
                    tail = head
                }
            }
            return node?.data
        }

        fun front(): T? = head?.data
        fun back(): T? = tail?.data

        fun size() = size
        fun empty() = size==0
        
        

        data class Node<T : Any>(
            val data: T,
            var next: Node<T>?,
            var prev: Node<T>?
        ) {
            constructor(data: T) : this(data, null, null)
        }
    }
}