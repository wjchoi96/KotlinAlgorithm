/*
    퀵정렬 이론, 구현 복습 파일
    https://blog.encrypted.gg/955
    https://underdog11.tistory.com/entry/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Quick-Sort-%ED%80%B5%EC%A0%95%EB%A0%AC-%ED%95%84%EC%88%98-%EA%B8%B0%EB%B3%B8-%EC%A0%95%EB%A6%AC-%EB%A1%9C%EB%AC%B4%ED%86%A0-%EB%B6%84%ED%95%A0-%ED%98%B8%EC%96%B4-%ED%9A%A8%EC%9C%A8%EC%A0%81%EC%9D%B8-%ED%94%BC%EB%B4%87%EA%B0%92-%EC%A0%95%ED%95%98%EA%B8%B0

    #피봇(Pivot)이란
    - 퀵정렬은 병합정렬과 동일하게 비교연산을 사용하고, 나누고 합치는 전략을 사용

    #간단하게 보는 QuickSort
    - List안에 있는 한 요소를 Pivot으로 선정해 List를 3개의 파티션으로 나눔
    1. pivot보다 작은 파티션
    2. pivot과 같은 수가 모인 파티션
    3. pivot보다 큰 파티션
    [ element < pivot | pivot | element > pivot ]

    pivot을 통해 3개의 파티션을 나누도록 도와주는 quickSortNavie함수
        1. List에 2개 이상의 element가 없다면 정렬된 상태로 정의
        2. List의 가운데 값을 pivot으로 지정
        3. List를 pivot을 이용해 3등분
        4. 반복문으로 정렬 후 합침
    - 매 단계마다 filter, 즉 O(n)에 3개의 파티션으로 분리
    - 크게보면 동작은 quickSort이지만, 파티션마다 List를 3개씩 생성해주기 떄문에 비효율적인 방식
    - QuickSort는 추가적인 공간을 사용하지 않는 정렬 알고리즘!

    #진짜 QuickSort란?
    - 매 단계마다 pivot이라고 이름 붙은 원소 하나를 제자리로 보내는 작업을 수행
        - 왼쪽에는 pivot보다 작은 값, 오른쪽에는 pivot보다 큰 값
    - 위 작업을 반복적으로 수행하여 정렬을 수행
    - QuickSort는 추가적인 공간이 필요하지 않다는 장점을 가짐
    즉, 추가적인 배열을 사용하지 않고 pivot을 제자리로 보내야 함 

    #pivot 선정
    1. 중간값 선정
    2. 마지막 값 선정 - 로무토
    3. 첫번째 값 전성 - 호어
    - 가장 이상적인 pivot은, 왼쪽에 pivot보다 작은값을 배치하고 오른쪽에 pivot보다 큰값을 배치했을때
    정확히 중간에 pivot이 위치하는것
    - 로무터, 호어같은 방식은 정렬된 List를 다시 정렬하려 하고, List에서 가장 앞이나 끝에 가장 큰 값이 있게 된다면
    O(N^2)의 시간복잡도. 즉, QuickSort의 최악의 시간복잡도가 나오게 됨
    - 때문에 중간값을 사용하는 QuickSort가 보편적으로 안전하다고 생각됨
    - 또한, 로무토와 호어는 중복값을 다르는데 효율적이지 않음

    #로무토
    1. 맨 마지막 element를 pivot으로 설정
    2. i는 pivot보다 작은 element의 개수
    - i의 초기값은 low
    - i는 우리가 pivot값보다 작은 값을 찾았을때, i와 위치를 바꿔주고 i를 1증가시킴
    3. 처음부터 pivot을 제외한 마지막-1(pivot은 미포함)까지 반복하여 element에 접근
    4. 현재 element(j)가 pivot과 작거나 같다면 i와 element의 위치를 바꿔줌. 그리고 i의 값을 1 증가시킴
        - i는 마지막으로 pivot보다 작거나 같은 값이 위치한곳의 다음 칸에 머물러 있고, j는 그 다음 pivot보다 작거나 같은 값을 찾아서 순회를 진행
        - j가 pivot보다 큰값을 만났다면, i와 swap해주고 i++
        - i는 pivot보다 작거나 같은 값이 위치한 곳의 다음 칸에 머무르게 됨
    5. 반복문이 끝났다면, i위치와 pivot의 위치를 바꿔줌
    6. pivot의 index를 리턴 

    => i를 low에서 시작하게 한다면, i의 현재 위치는 pivot보다 작은 값들로 확정된 마지막 element의 다음 index
    => i를 low-1에서 시작하게 한다면, i의 현재 위치는 pivot보다 작은 값들록 확정된 마지막 element의 index

    => 배열을 순회하며 현재 element(j)가 pivot보다 작거나 같다면, swap(i++, j)
        - 이때 i가 low-1에서 시작한것이라면 swap(++i, j)
    => i를 기준으로 배열의 앞쪽엔 pivot보다 작거나 같은 값, 오른쪽엔 큰값이 오게 됨
    => pivot 이전 Item까지 순회를 마치고, 순회를 종료한 뒤, swap(i, pivot)을 진행
        - i가 low-1에서 시작한것일면 swap(++i, pivot)
    => O(n)에 low ~ pivot ~ high가 자연스럽게 배치됨
    => i를 리턴
        - low ~ i 는 pivot보다 작거나 같은 그룹
        - i+1 ~ high 는 pivot보다 큰 그룹

    #호어
    -> 로무토를 이해했다면, 그와 반대로 수행하면 될것 같음
    -> 호어는 로무토와 다르게 포인터를 맨앞과 맨 뒤에두고 시작
    -> 포인터를 end에 2개 놓고 하는 방식이나, 포인터를 start+1, end에 두고 하는 방식의
    정렬 과정은 동일하다


    #중간값
    - 가장 첫번째 값, 중간값, 마지막 값의 중간값을 구하여 pivot선정
    - 가장 큰값, 작은값을 pivot으로 선정하는것을 피할 수 있음
    - pivot을 선정 후, high나 low로 pivot을 위치시킨 뒤 로무토나 호어 방식으로 수행하면 됨
    => 수행 결과, 확실히 pivot이 균등하게 잡히는것을 확인 가능


    #연습문제 검증
    #로무토 방식
    1. 시간초과(32%)
    - https://www.acmicpc.net/board/view/31887
    - 직접 구현한 quickSort는 최악의 경우 O(n^2)가 나오기 떄문에 당연한것 같기도 함
    - 해당 문제에서는 수가 중복되지 않기 때문에, pivot을 가운데다가 두면 비교적 잘 되지만 이라는 언급이 있음
        -> 로무토 방식은 잘 구현해도 시간초과가 날것

    #호어 방식
    1. 시간초과 32%
    - 직접 구현한 QuickSort는 최악의 경우 O(N^2)

    #중간값 방식
    1. 시간초과 32%


*/

fun main() {
    BaseAlgorithmQuickSort().solve()
}

class BaseAlgorithmQuickSort {
    fun solve() {
        // val bw = System.out.bufferedWriter()
        // val br = System.`in`.bufferedReader()

        // val n = br.readLine().toInt()
        // val arr = Array(n){0}
        // repeat(n) {
        //     arr[it] = br.readLine().toInt()
        // }

        // val sortedArr = arr.toMutableList().apply {
        //     quickSortLomuto(0, this.size-1)
        // }
        // sortedArr.forEach {
        //     bw.write("$it\n")
        // }
    
        // bw.flush()
        // bw.close()
        // br.close()

        val temp = mutableListOf(2, 5, 1, 3, 10, 9, 5, 3, 11, 4)
        // val temp = mutableListOf(6, -8, 1, 12, 8, 15, 7, -7)
        temp.quickSortMedian(0, temp.size-1)
        println("${temp}")
    }

    fun <T : Comparable<T>> List<T>.quickSortNavie(): List<T> {
        if(this.size < 2) return this
        val pivot = this[this.size/2] // middle 값을 pivot 선정
        
        val less = this.filter{it<pivot}
        val equal = this.filter{it==pivot}
        val greater = this.filter{it>pivot}

        return less.quickSortNavie() + equal + greater.quickSortNavie() // 정렬한 후 합침
    }

    //start에 포인터를 두개 둠
    fun <T : Comparable<T>> MutableList<T>.quickSortLomuto(start: Int, end: Int) {
        if(end-start < 1){ // element가 1개가 되면 base condition
            return 
        }
        val pivot = this[end]
        
        var i = start
        for(j in start until end) {
            if(this[j] <= pivot) {
                this.swap(i, j)
                i++
            }
        }
        this.swap(i, end)
        println("pivot[$pivot] => $this")
        quickSortLomuto(start, i-1)
        quickSortLomuto(i+1, end)
    }

    // 양 끝에 포인터를 두는 호어방식 -> 바킹독님
    fun <T : Comparable<T>> MutableList<T>.quickSortHore(start: Int, end: Int) {
        if(end-start < 1) {
            return
        }
        val pivot = this[start]

        var left = start+1
        var right = end
        while(true) {
            while(left <= right && this[left] <= pivot) left++
            while(left <= right && this[right] > pivot) right--
            if(left > right) break
            swap(left, right)
        }
        swap(right, start)
        println("pivot[$pivot] => $this")
        quickSortHore(start, right-1)
        quickSortHore(right+1, end)
    }

    // 로무토 방식의 반대로 직접 구현해본 호어 => end에 포인터 두개
    fun <T : Comparable<T>> MutableList<T>.quickSortHoreSelf(start: Int, end: Int) {
        if(end-start < 1) {
            return
        }
        val pivot = this[start]

        var i = end
        for(j in end downTo start+1) { // start 이전까지 아래로 순회
            if(this[j] >= pivot) {
                swap(i, j)
                i--
            }
        }
        swap(i, start)
        println("pivot[$pivot] => $this")
        quickSortHore(start, i-1)
        quickSortHore(i+1, end)
    }

    fun <T : Comparable<T>> MutableList<T>.quickSortMedian(start: Int, end: Int) {
        val mid = (start + end)/2
        if(this[start] > this[mid]) {
            swap(start, mid)
        }
        if(this[end] < this[start]) {
            swap(start, end)
        }
        if(this[end] < this[mid]) {
            swap(mid, end)
        }

        swap(start, mid) // mid와 start를 swap하여 pivot을 start로 위치시킴
        quickSortHore(start, end) // 호어 퀵정렬 실시
    }

    private fun <T> MutableList<T>.swap(from: Int, to: Int) {
        val t = this[to]
        this[to] = this[from]
        this[from] = t
    }
}