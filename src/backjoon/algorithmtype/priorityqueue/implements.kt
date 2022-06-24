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

fun main(args: Array<String>){
    SolutionPriorityQueue().solve()
}
class SolutionPriorityQueue {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()
    
        bw.flush()
        bw.close()
        br.close()
    }

    class PriorityQueue {

    }
}