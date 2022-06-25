/*
    바킹독님 알고리즘 강의 중 우선순위큐 단원 구현 코드
    sliver 2

    최소 힙
    implements.kt 에서 구현한 최소 힙 코드 유효성 판단

    - 배열에 자연수 x를 넣는다
    - 배열에서 가장 작은 값을 출력하고, 그 값을 배열에서 제거

    1초

    연산의 개수 N
    1<= N <= 100,000

    x가 자연수라면 x를 추가
    x가 0ㅇ라면 배열에서 가장 작은 값을 출력하고, 값을 제거

    0<= x <= 2^31
*/

/*
    제출
    1. 런타임 에러(7%)[ArrayIndexOutOfBouds]
    - size 설정 안했다

    2. 틀렸습니다(7%)

    3. 틀렸습니다(7%)
    - setTreeBalanceFromRoot 개선

    4. 틀렸습니다(7%)
    - setTreeBalanceFromRoot 개선

    5. 맞았습니다
    - setTreeBalanceFromLeaf 개선
    - PriorityQueueB

    6.[개선] 성공
    - PriorityQueue

*/
/*
    개선
    바킹독님 코드를 보며 개선할 것 개선
    
    1. hasParent 를 isRoot로 변경하면 될듯
    2. setTreeBalanceFromRoot 개선
*/
fun main(args: Array<String>){
    Solution1927().solve()
}
class Solution1927 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val queue = PriorityQueue()
        repeat(br.readLine().toInt()){
            val x = br.readLine().toInt()
            when(x){
                0 -> bw.write("${queue.pop()}\n")
                else -> queue.push(x)
            }
        }
    
        bw.flush()
        bw.close()
        br.close()
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

        fun pop(): Int {
            val node = queue[1]
            if(node != null){
                remove(1)
            }
            return node ?: 0 // 값이 없다면 0 출력
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
            left, right 중 더 작은 값을 구한다
            min 값과 parent 값을 비교하여 교체 여부 판단
        */
        private fun setTreeBalanceFromRoot(root: Int){
            var idx = root
            while(idx.hasChild()){
                val parent = queue[idx]!!
                val left = queue[idx*2] ?: Int.MAX_VALUE
                val right = queue[idx*2+1] ?: Int.MAX_VALUE
                var swapIdx = idx*2 // left를 min 값으로 설정
                if(left > right) swapIdx = idx*2+1 // right가 left보다 작다면 right를 min값으로 설정
                if(parent <= queue[swapIdx]!!) break // 
                swap(idx, swapIdx)
                idx = swapIdx
            }
        }        
        // 트리의 구조를 유지하며, 최소힙에 위배되지 않도록 leaf 부터 root까지 거슬러 올라가며 체크, swap 한다
        private fun setTreeBalanceFromLeaf(leaf: Int){
            var idx = leaf
            while(!idx.isRoot()){
                 //chlid가 parent보다 크거나 같아야 한다. 반대라면 swap 해준다
                if(queue[idx]!!<queue[idx/2]!!){
                    swap(idx, idx/2)
                }
                idx = idx/2
            }
        }        

        private fun Int.isRoot(): Boolean = this == 1
        private fun Int.hasChild(): Boolean = this.hasRightChild() || this.hasLeftChild()
        private fun Int.hasRightChild(): Boolean = (this*2+1 in 1 until queue.size && queue[this*2+1] != null)
        private fun Int.hasLeftChild(): Boolean = (this*2 in 1 until queue.size && queue[this*2] != null)
        private fun swap(idx1: Int, idx2: Int){
            val t = queue[idx1]
            queue[idx1] = queue[idx2]
            queue[idx2] = t
        }
    }
}


/*

틀렸던 반례
11
2
2
3
3
1
0
0
0
0
0
0
==== aws
1
2
2
3
3
0
output === 
2
1
2
3
3
0


11
2 [2]
2 [2, 2]
3 [2, 2, 3]
3 [2, 2, 3, 3]
1 [1, 2, 3, 3, 2] \
0 1
0 2
0 3
0
0
0

[2, 2, 3, 3] 에서 push 1
[2, 2, 3, 3, 1]
[2, 1, 3, 3, 2]
[1, 2, 3, 3, 2]

[1, 2, 3, 3, 2] 에서 pop
[2, 2, 3, 3, 1]
[2, 2, 3, 3]


*/

// first solve
class Solution1927B {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val queue = PriorityQueue()
        repeat(br.readLine().toInt()){
            val x = br.readLine().toInt()
            when(x){
                0 -> bw.write("${queue.pop()}\n")
                else -> queue.push(x)
            }
        }
    
        bw.flush()
        bw.close()
        br.close()
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

        fun pop(): Int {
            val node = queue[1]
            if(node != null){
                remove(1)
            }
            return node ?: 0 // 값이 없다면 0 출력
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
                    // right != null -> always true 경고 보기 싫어서 true 로 설정
                    true -> { // 둘 다 존재
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