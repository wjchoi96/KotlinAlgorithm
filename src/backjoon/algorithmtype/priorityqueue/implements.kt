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
/*
    개선된 코드는 1927.kt
    - Solution1927.PriorityQueue 
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
        //1-based-index 로 처리해야한다
        private val queue: Array<Int?> = Array(100001){null}
        private var itemSize: Int = 0 
        val size: Int
            get() = if(itemSize<1) 0 else itemSize

        fun push(x: Int){
            //마지막 leaf에 추가하고, 올라오면서 유효성 체크하면서 swap?
            queue[++itemSize] = x // leaf 에 추가
            setTreeBalanceFromLeaf(itemSize)
        }

        fun pop(): Int? {
            val node = queue[1]
            if(node != null){
                remove(1)
            }
            return node 
        }
        fun top(): Int? = queue[1]

        private fun remove(at: Int){
            // 지울 대상과 현재 leaf의 위치를 바꾸고, leaf 위치의 데이터 제거
            // leaf에 있던 데이터가 위치한 곳을 root 로 하여 해당 서브트리 setTreeBalanceFromRoot 실행
            swap(at, itemSize)
            queue[itemSize--] = null
            setTreeBalanceFromRoot(at)
        }

        /*
            트리의 왼쪽 아이템이 없다면 오른쪽 아이템은 있을 수 없다
            왼쪽이 없다면 모두 없는것

            왼쪽만 있다면 -> left 와 parent 를 비교하여, 더 작은것을 parent로 적용
            둘다 있다면 -> 
            1. left 와 rigth 중 parent 보다 작은게 있다면 변경
            2. 둘다 parent 보다 작다면 -> 둘중 더 작은 것으로 변경
        */
        private fun setTreeBalanceFromRoot(root: Int){
            var idx = root
            while(hasChild(idx)){
                val parent = queue[idx]!!
                val left = if(hasLeftChild(idx)) queue[idx*2] else null
                val right = if(hasRightChild(idx)) queue[idx*2+1] else null
                // println("left[$left][${idx*2}], right[$right][${idx*2+1}]")
                var swapIdx: Int? = null
                when {
                    left == null -> {} // 둘 다 없는 것
                    right == null -> { // left 만 존재
                        if(parent > left){
                            swapIdx = idx*2
                        }
                    }
                    right != null -> { // 둘 다 존재
                        if(parent > left && parent > right){ // 둘다 parent보다 작다면 -> 둘중 더 작은것으로 swap
                            if(left<right){ // 이때 왠만하면 오른쪽값에 parent가 가도록 = 연산은 오른쪽이 우선이 되도록 설정
                                swapIdx = idx*2
                            }else{
                                swapIdx = idx*2+1
                            }
                        }else{ //둘다 parent 보다 크거나, 하나만 parent보다 작은경우 -> 작은 경우가 있다면 swap
                            if(parent>left){
                                swapIdx = idx*2
                            }else if(parent>right){
                                swapIdx = idx*2+1
                            }
                        }
                    }
                }
                if(swapIdx != null){
                    swap(idx, swapIdx)
                    idx = swapIdx
                }else  // -> 알맞는 위치에 위치한것
                    break
            }
        }        
        // 트리의 구조를 유지하며, 최소힙에 위배되지 않도록 leaf 부터 root까지 거슬러 올라가며 체크, swap 한다
        private fun setTreeBalanceFromLeaf(leaf: Int){
            var idx = leaf
            while(hasParent(idx)){
                val child = queue[idx]!!
                val pIdx = idx/2
                val parent = queue[pIdx]!!
                // println("idx[$idx] has parent[$pIdx] child[$child] parent[$parent]")
                if(child<parent){ //chlid가 parent보다 크거나 같아야 한다. 반대라면 swap 해준다
                    swap(idx, pIdx)
                }
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