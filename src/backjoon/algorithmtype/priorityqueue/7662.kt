/*
    바킹독님 알고리즘 강의 중 이진검색트리, 우선순위큐 단원 연습문제
    gold 4

    Kotlin의 이진검색트리로 이루어져있고, 중복이 허용되는 자료구조가 존재하지 않아
    우선순위큐를 사용하여 풀이

    이중 우선순위 큐

    이중 우선순위 큐는 전형적인 우선순위 큐처럼 데이터를 삽입, 삭제할 수 있는 자료구조
    전형적인 큐와의 차이점은 데이터를 삭제할 때 연산 명령에 따라 우선순위가 가장 높은 데이터 또는 가장 낮은 데이터 중 하나를 삭제하는 점이다
    두 가지 연산이 사용된다
    - 데이터를 삽입
    - 데이터를 삭제
        우선순위가 가장 높은 것을 삭제
        우선순위가 가장 낮은 것을 삭제
    정수만 저장하는 이중 우선순위 큐 Q가 존재
    각 정수의 값 자체를 우선순위라고 간주

    Q에 적용될 일련의 연산이 주어질 때 이를 처리한 후, 최종적으로 Q에 저장된 데이터 중 최댓값과 최솟값을 출력

    6초
    T개의 테스트 데이터
    연산의 개수 k
    k <= 1,000,000

    연산을 나타내는 문자 'D' or 'I'와 정수 n
    I n : 정수 n을 insert
    동일한 정수가 삽입될 수 있다
    D 1 : Q에서 최댓값을 삭제
    D -1: Q에서 최솟값을 삭제

    최댓값 혹은 최솟값이 여러개라면, 그 중 하나만 제거된다

    모든 연산을 처리한 후 Q에 남아있는 값 중 최댓값과 최솟값을 출력
    Q가 비어있다면 EMPTY를 출력
*/
/*
    1. 중복된 값이 있을 수 있다 했으니 TreeSet, TreeMap 사용 불가
    2. 우선순위큐는 최대힙 또는 최소힙 구조 중 하나만을 택해야 하는데, 최댓값과 최솟값 모두를 기억해야한다
    => 둘다 약간씩의 문제가 존재

    이진검색트리 + 자가균형트리 구현은 불가능에 가깝다
    힙에서 최소, 최댓값을 모두 기억할 수는 없다
    => 어느쪽이든 직접 구현도 불가능

    #최소힙, 최대힙 2개를 생성하여 문제 풀이 진행
    
    !maxHeap에서 제거된 원소가 minHeap에서 살아있는 문제 존재
    - count 를 추가하여 empty인 상태를 구분하려 했으나, 근본적인 원인은 해결 불가
    - HashMap에 실제 데이터를 넣어놓고 poll 수행 시, map 에서 유효한 데이터인지 확인
    => 참고: https://girawhale.tistory.com/14

    #TreeMap 으로 Key는 입력값으로,value는 입력 횟수로 해서 풀이해도 될것같은데
    
*/
/*
    제출
    1. 틀렸습니다(26%)
    - 문제점 인식
    - https://girawhale.tistory.com/14 를 참고하며 문제점을 해결해보자

    2. 틀렸습니다(92%)
    - 2147483647 -2147483648 int형 범위에 대한 오버플로우
    - maxQueue의 compareBy 에서 -2147483648 이 -it 처리가 되면 2147483648가 되는데, int형의 최대 범위는 2147483647 이기 때문에 오버플로우 발생

    3. 성공
    - 우선순위 큐 구현 성공 - Solution7662

    4. 성공
    - TreeMap 구현 성공 - Solution7662UseBST
*/
/*
    - TreeMap으로도 구현해보자
    #TreeMap 주요 메소드 
    https://codedragon.tistory.com/6142
    lastKey // 제일 큰 key를 반환
    firstKey // 제일 작은 key 반환

    map.pollLastEntry() // 제일 큰 키를 반환하며 제거
    map.pollFirstEntry() // 제일 작은 키를 반환하며 제거
*/

import java.util.PriorityQueue
import java.util.HashMap
import java.util.TreeMap
fun main(args: Array<String>){
    Solution7662UseBST().solve()
}
class Solution7662UseBST {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        repeat(br.readLine().toInt()){
            val map = TreeMap<Long, Int>()
            repeat(br.readLine().toInt()){
                br.readLine().split(' ').let { 
                    val v = it[1].toLong()
                    when(it[0]){
                        "I" -> map[v] = map.getOrDefault(v, 0)+1
                        "D" -> {
                            if(map.size != 0){
                                when(v){
                                    1.toLong() -> {
                                        val k = map.lastKey() // 제일 큰 키를 반환
                                        if(map.getOrDefault(k, 1) == 1)
                                            map.remove(k)
                                        else
                                            map[k] = map[k]!!-1
                                    }
                                    else -> {
                                        val k = map.firstKey() // 제일 작은 키를 반환
                                        if(map.getOrDefault(k, 1) == 1)
                                            map.remove(k)
                                        else
                                            map[k] = map[k]!!-1
                                    }
                                }
                            }
                        }
                        else -> {}
                    }
                }
            }
            if(map.size==0){
                bw.write("EMPTY\n")
            }else{
                bw.write("${map.lastKey()} ${map.firstKey()}\n")
            }
        }

        bw.flush()
        bw.close()
        br.close()
    }
}
// solve use priority queue
class Solution7662 {
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        repeat(br.readLine().toInt()){
            val minQueue = PriorityQueue<Long>()
            val maxQueue = PriorityQueue<Long>(compareBy(
                {-it}
            ))
            val map: HashMap<Long, Int> = HashMap()
            repeat(br.readLine().toInt()){
                br.readLine().split(' ').let { 
                    val v = it[1].toLong()
                    when(it[0]){
                        "I" -> {
                            minQueue.offer(v)
                            maxQueue.offer(v)
                            map[v] = map.getOrDefault(v, 0) + 1
                        }
                        "D" -> {
                            if(map.size != 0){
                                removeFromMap(map, if(v==1.toLong())maxQueue else minQueue)
                            }
                        }
                    }
                }
            }
            // 최대값이 map 에 있는 값이 되도록 적용
            while(!maxQueue.isEmpty()){
                val item = maxQueue.peek()
                if(!map.contains(item)) {
                    maxQueue.poll()
                }else{
                    break
                }
            }
            // 최소값이 map 에 있는 값이 되도록 적용
            while(!minQueue.isEmpty()){
                val item = minQueue.peek()
                if(!map.contains(item)) {
                    minQueue.poll()
                }else{
                    break
                }
            }
            bw.write("map: ${map}\n")
            bw.write("min: ${minQueue}\n")
            bw.write("max: ${maxQueue}\n")
            if(map.isEmpty()){
                bw.write("EMPTY\n")
            }else {
                bw.write("${maxQueue.peek()} ${minQueue.peek()}\n")
            }
        }

        bw.flush()
        bw.close()
        br.close()
    }
    private fun removeFromMap(map: HashMap<Long, Int>, queue: PriorityQueue<Long>): Long{
        var num: Long
        while(true){
            num = queue.poll() // 값을 지운다
            val c = map.getOrDefault(num, 0)
            when(c){
                0 -> continue // 없다면 현재 값은 이미 지워진 값이니, 다음 값을 poll 한다
                1 -> map.remove(num)
                else -> map[num] = map[num]!!-1
            }
            break
        }
        return num
    }
}
/*
1
3
I 5
D -1
I 4
=== output
5 4
=== aws
4 4

https://www.acmicpc.net/board/view/81295 => 반례모음
모두 통화


1
2
I -2147483648
I 2147483647
==output==
-2147483648 -2147483648
==aws==
2147483647 -2147483648

maxQueue의 compareBy 에서 -2147483648 이 -it 처리가 되면 2147483648가 되는데, int형의 최대 범위는 2147483647 이기 때문에 오버플로우 발생
*/