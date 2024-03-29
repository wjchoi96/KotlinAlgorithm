/*
    바킹독님 알고리즘 강의 중 이진 검색 트리 단원 연습문제
    gold 4

    우선순위 큐 문제이지만, 문제가 괜찮아서 소개
    우선순위 큐를 모르더라도 bst로 풀이 가능
    삽입, 최댓값 삭제, 최솟값 삭제 구현 시 배열로 단순히 구현한다면 O(k^2)가 된다는건 쉽게 감이 올것
    해시를 사용하자니, 해시테이블 안의 원소 중 최갯값, 최소값을 효율적으로 찾을 방법이 없다
    그런데, 이진검색트리가 딱 이 문제의 상황에 적합하다

    이중 우선순위 큐
    https://www.acmicpc.net/problem/7662

    전형적인 우선순위 큐처럼 데이터를 삽입, 삭제할 수 있는 자료 구조
    차이점은 
    - 데이터를 삭제할 때 연산명령에 따라 우선순위가 가장 높은 데이터, 또는 가장 낮은 데이터 중 하나를 삭제하는 점이다

    이중 우선순위 큐를 위해선 두 가지 연산이 사용된다
    - 데이터를 삽입
    - 데이터를 삭제
        - 우선순위가 가장 높은것을 삭제 / 우선순위가 가장 낮은것을 삭제

    정수만 저장하는 이중 우선순위 큐 Q
    Q에 저장된 각 정수의 값 자체를 우선순위라고 간주
    => 값이 높으면 우선순위가 높고, 낮으면 우선순위가 낮은것

    6초
    T개의 테스트 케이스
    Q에 적용할 연산의 개수 k
    k <= 1,000,000

    연산을 나타내는 문자 D,I 와 정수 n
    I n : 정수 n을 Q에 삽입, 동일한 정수가 삽입될 수 있다
    D 1 : Q에서 최댓값을 제거
    D -1: Q에서 최솟값을 제거

    최댓값, 최소값이 둘 이상이라면 하나만 제거된다
    Q가 비어있는데 D연산이 들어온다면 무시
    Q에 저장될 모든 정수는 32-비트 정수

    각 테스트 데이터에 대해, 모든 연산을 처리 후 Q에 남아있는 최댓값과 최솟값을 출력
    Q가 비어있다면 EMPTY 출력

*/
/*
    C++ 에는 bst로 이루어진 자료구조 set, map, multiset 중 multiset이 중복을 허용해서 multiset를 사용하여 풀이가 가능하지만
    kotlin 은 TreeSet, TreeMap 모두 중복을 허용하지 않아 풀이가 불가능한 것 같다 

    다음 단원 우선순위 큐를 공부하고 다시 풀어보자
    => priorityQueue 의 7662.kt 에 구현 성공

    #TreeMap으로 구현 가능하다. 
    => Solution7662UseBST
*/
/*
    TreeMap으로 구현
    #TreeMap 주요 메소드 
    https://codedragon.tistory.com/6142
    lastKey // 제일 큰 key를 반환
    firstKey // 제일 작은 key 반환

    map.pollLastEntry() // 제일 큰 키를 반환하며 제거
    map.pollFirstEntry() // 제일 작은 키를 반환하며 제거
*/
/*
    제출
    1. 성공
*/

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