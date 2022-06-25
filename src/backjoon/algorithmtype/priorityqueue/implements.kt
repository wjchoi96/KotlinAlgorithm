/*
    바킹독님 알고리즘 강의 중 우선순위큐 단원 구현 코드

    이진 트리 구조를 코드로 표현하는 방법 
    => 힙에 한해서 각 원소를 배열로 대응시켜 다소 간단하게 나타낼 수 있다
    
    Tree
    8
    12      20
    16 14   21  27
    =>
    1-based-index, 1-index인 8이 루트
    [-, 8, 12, 20, 16, 14, 21, 27] 
    x번지의 왼쪽, 오른쪽자식: 2x, 2x+1
    x번지의 부모: x/2
*/
/*
    test code 성공
*/

fun main(args: Array<String>){
    SolutionPriorityQueue().solve()
}
class SolutionPriorityQueue {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        test()
    
        bw.flush()
        bw.close()
        br.close()
    }
    private fun test(){
        val queue = PriorityQueue()
        queue.push(10) // [10]
        queue.push(2) // [2, 10]
        queue.push(5) // [2, 5, 10] or [2, 10, 5]
        queue.push(9) // [2, 5, 9, 10] or [2, 9, 5, 10]
        println("${queue.top()}") // 2
        queue.pop() // pop 2, [5, 9, 10] or [5, 10, 9]
        queue.pop() // pop 5, [9, 10]
        println("${queue.top()}") // 9
        queue.push(5) // [5, 9, 10] or [5, 10, 9]
        queue.push(15) // [5, 9, 10, 15] or [5, 10, 9, 15]
        println("${queue.top()}") // 5
        queue.pop() // pop 5, [9, 10, 15] or [9, 10, 15]
        println("${queue.top()}") // 9
    }

    // 최소힙 구현 해보자
    class PriorityQueue {
        //size는 10000으로 초기화
        //1-based-index 로 처리해야한다
        private val queue: Array<Int?> = Array(10000){null}
        private var itemSize: Int = 0 
        val size: Int
            get() = if(itemSize<1) 0 else itemSize

        fun push(x: Int){
            //마지막 leaf에 추가하고, 올라오면서 유효성 체크하면서 swap?
            queue[++itemSize] = x // leaf 에 추가
            setTreeBalanceFromLeaf(itemSize)
            printQueue()
        }

        fun pop(): Int? {
            val node = queue[1]
            remove(0)
            printQueue()
            return node
        }
        fun top(): Int? = queue[1]

        private fun remove(at: Int){
            // 지울 대상과 현재 leaf의 위치를 바꾸고, leaf 위치의 데이터 제거
            // leaf에 있던 데이터가 위치한 곳을 root 로 하여 해당 서브트리 setTreeBalanceFromRoot 실행
            swap(at, itemSize)
            queue[itemSize] = null
            itemSize--
            setTreeBalanceFromRoot(at)
        }

        private fun setTreeBalanceFromRoot(root: Int){
            var idx = root
            while(hasChild(idx)){
                val left = if(hasLeftChild(idx)) queue[idx*2] else null
                val right = if(hasRightChild(idx)) queue[idx*2+1] else null
                // println("left[$left][${idx*2}], right[$right][${idx*2+1}]")
                //9,10 이 반복되네
                when {
                    left != null && right != null -> {
                        idx = if(left <= right){
                            swap(idx, idx*2)
                            idx*2
                        }else {
                            swap(idx, idx*2+1)
                            idx*2+1
                        }
                    }
                    left != null -> {
                        swap(idx, idx*2)
                        idx = idx*2
                    }
                    right != null -> {
                        swap(idx, idx*2+1)
                        idx = idx*2+1
                    }
                }
            }
        }        
        // 트리의 구조를 유지하며, 최소힙에 위배되지 않도록 leaf 부터 root까지 거슬러 올라가며 체크, swap 한다
        private fun setTreeBalanceFromLeaf(leaf: Int){
            var idx = leaf
            var node = queue[idx]!!
            while(hasParent(idx)){
                // println("parent[$idx]")
                var pIdx = idx/2
                var p = queue[pIdx]!!
                //node가 p보다 커야한다(같아도 된다). 반대라면 swap 해준다
                if(node<p){
                    swap(idx, pIdx)
                }
                node = p
                idx = pIdx
            }
        }        
        private fun hasChild(idx: Int): Boolean = hasRightChild(idx) || hasLeftChild(idx)
        private fun hasRightChild(idx: Int): Boolean = (idx*2+1 in 1 until queue.size && queue[idx*2+1] != null)
        private fun hasLeftChild(idx: Int): Boolean = (idx*2 in 1 until queue.size && queue[idx*2] != null)
        private fun swap(idx1: Int, idx2: Int){
            val t = queue[idx1]
            queue[idx1] = queue[idx2]
            queue[idx2] = t
        }
        private fun hasParent(idx: Int): Boolean{
            return (idx/2) in 1 until queue.size && queue[idx/2] != null
        }

        private fun printQueue(){
            print("[ ")
            for(i in 1 until queue.size){
                if(queue[i] == null) {
                    break
                }
                print("${queue[i]} ")
            }
            println("]")
        }
    }
}