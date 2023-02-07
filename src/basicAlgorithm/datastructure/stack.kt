/*
    LinkedList를 사용한 Stack 구현 복습

    배열을 사용한 방식도있지만, 용량이 제한된다는 단점으로 인해
    단순 Stack을 구현한다면 LinkedList가 더 효율적일것으로 보임
    - 용량 관리 코드가 필요 없음

    #검증 연습문제
    - 백준 10828
    1. 성공

    #포인트
    - Stack은 Head하나만 둬도 됨
*/

import java.util.StringTokenizer
fun main() {
    BaseAlgorithmStack().solve()
}
class BaseAlgorithmStack {
    fun solve() {
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
        var st : StringTokenizer

        val size = br.readLine().toInt()
        val stack = StackUseLinkedList<Int>()
    
        for(i in 0 until size){
            st = StringTokenizer(br.readLine())
            val operation = st.nextToken()
            when(operation){
                "push" -> stack.push(st.nextToken().toInt())
                "pop" -> bw.write("${stack.pop() ?: -1}\n")
                "size" -> bw.write("${stack.size()}\n")
                "empty" -> bw.write("${if(stack.empty()) 1 else 0}\n")
                "top" -> bw.write("${stack.peek() ?: -1}\n")
            }
        }
    
        bw.flush()
        bw.close()
        br.close()
    }

    class StackUseLinkedList<T : Any> {
        private var head: Node<T>? = null // pop될 node를 가리킴
        private var size = 0

        fun push(data: T){
            val newNode = Node(data)
            when (head) {
                null -> { // 데이터가 없음
                    head = newNode
                }
                else -> {
                    head?.next = newNode
                    newNode.prev = head
                    head = newNode
                }
            }
            size++
        }
        // 1, 2, 3[HEAD] => 1, 2, 3, 4[HEAD]
        // 1[HEAD]
        // 1, 2[HEAD]

        fun pop(): T? {
            val node = when(head) {
                null -> null
                else -> {
                    size--
                    head?.next?.prev = null
                    head?.prev?.next = null
                    head
                }
            }
            head = head?.prev
            return node?.data
        }

        fun peek(): T? {
            return head?.data
        }

        fun size(): Int = size

        fun empty(): Boolean = size==0

        data class Node<T : Any>(
            val data: T,
            var prev: Node<T>?,
            var next: Node<T>?
        ) {
            constructor(data: T) : this(data, null, null)
            constructor(data: T, prev: Node<T>?) : this(data, prev, null)

            override fun toString(): String {
                return "data[$data] prev[${prev?.data}], next[${next?.data}]"
            }
        }
    }
}