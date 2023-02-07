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
    - setHeapBalnceFromRoot: root부터 순회해 내려오며 heap 구조를 위반하는 경우를 점검, 필요하다면 swap을 통해 해결
    - setHeapBalnceFromLeaf: leaf부터 순회해 올라가며 heap 구조를 위반하는 경우를 점검, 필요하다면 swap을 통해 해결

    #Heap 삽입
    1. leaf에 insert
    2. setHeapBalnceFromLeaf() 수행

    #Heap 제거
    - heap의 삭제 연산은 root를 제거하는것
    1. root와 left leaf를 swap
    2. left leaf 제거
    3. setHeapBalnceFromRoot() 수행

*/

class BaseAlgorithmHeap {
    fun solve() {

    }

    class HeadArray<T : Any>(
        private val compareator: Compareator<T>
    ) {

    }
}