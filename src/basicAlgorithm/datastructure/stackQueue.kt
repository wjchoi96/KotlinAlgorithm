/*
    Stack 2개로 Queue 구현

    #검증 연습문제
    백준 10845
    1. 틀렸습니다(1%)
    - poll시에 _back 갱신 코드 추가
    - _back이 null이 되는 경우가 없었음

    2. 성공
*/

import java.util.Queue
import java.util.Stack
fun main(){
    BaseAlgorithmStackQueue().solve()
}
class BaseAlgorithmStackQueue {
    fun solve() {
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val queue = StackUseQueue<Int>()

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

    class StackUseQueue<T : Any> {
        private val buffer: Stack<T> = Stack()
        private val printer: Stack<T> = Stack()

        private fun clearBuffer(){
            if(printer.isEmpty() && buffer.isNotEmpty()){
                while(!buffer.isEmpty()){
                    printer.push(buffer.pop())
                }
            }
        }
        
        private var _back: T? = null
            
        fun offer(data: T) {
            _back = data
            buffer.push(data)
        }

        fun poll(): T? {
            clearBuffer()
            return if(printer.isEmpty()){
                null
            }else {
                printer.pop().also {
                    if(it == _back) _back = null
                }
            }
        }

        fun size(): Int {
            return buffer.size + printer.size
        }

        fun empty(): Boolean {
            return buffer.isEmpty() && printer.isEmpty()
        }

        fun front(): T? {
            clearBuffer()
            return if(printer.isEmpty()){
                null
            }else {
                printer.peek()
            }
        }

        fun back(): T? = _back
    }
}