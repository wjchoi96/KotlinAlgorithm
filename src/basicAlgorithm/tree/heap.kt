/*
    Heap 구현
    https://st-lab.tistory.com/205
    - 배열을 이용한 구현
    - 특정 노드의 검색, 이동 과정을 LinkedList보다 더 편하게 관리 가능

    #1-based index 구조
    - startIdx = 1
    - 0은 편의상 비워둠

    #node 접근
    node*2 -> 왼쪽 자식
    node*2 + 1 -> 오른 자식
    node/2 -> node의 부모

    #필요한 메소드
    - 동적 resize: 더 큰 size의 배열을 생성 후 item들을 옮김
    - swap: node swap
    - siftDown: root부터 순회해 내려오며 heap 구조를 위반하는 경우를 점검, 필요하다면 swap을 통해 해결
    - siftUp: leaf부터 순회해 올라가며 heap 구조를 위반하는 경우를 점검, 필요하다면 swap을 통해 해결

    #Heap 삽입
    1. leaf에 insert
    2. siftUp() 수행

    #Heap 제거
    - heap의 삭제 연산은 root를 제거하는것
    1. root와 left leaf를 swap
    2. left leaf 제거
    3. siftDown() 수행

*/
fun main() {
    BaseAlgorithmHeap().solve()
}
class BaseAlgorithmHeap {
    fun solve() {

    }

    class MinHeapArray() {
        private val root = 1
        private var comparator: Comparator<Int> = compareBy{it}
        private val DEFAULT_CAPACITY = 10
        private var queue: Array<Int?> = Array(DEFAULT_CAPACITY){null}
        private var size = 0

        private fun resize(newCapacity: Int) {
            val newQueue = Array<Int?>(newCapacity){null}
            if(size != 0){
                for(i in 1 until 0+size){
                    newQueue[i] = queue[i]
                }
            }
            queue = newQueue
        }

        private fun siftUp(from: Int, target: Int) {
            var idx = from
            while(idx != root){
                val parentIdx = idx/2
                val parent = queue[parentIdx]

                //target이 부모보다 크면 반복문 종료
                if(comparator.compare(target, parent) >= 0){
                    break
                }

                idx = parentIdx
            }
        }

        private fun Int.isRoot(): Boolean = this == root
        private fun Int.getParent(): Int? = queue[this/2]
        private fun Int.getLeft(): Int? = queue[this*2]
        private fun Int.getRight(): Int? = queue[this*2 + 1] 
        private fun Int.hasRight(): Boolean = (this*2+1 in 1 until queue.size && queue[this*2+1] != null)
        private fun Int.hasLeft(): Boolean = (this*2 in 1 until queue.size && queue[this*2] != null)
        
        private fun swap(idx1: Int, idx2: Int) {
            val t = queue[idx1]
            queue[idx1] = queue[idx2]
            queue[idx2] = t
        }

    }
}