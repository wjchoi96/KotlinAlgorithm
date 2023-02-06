/*
    병합정렬 이론, 구현 연습 파일
    https://blog.encrypted.gg/955
    https://underdog11.tistory.com/entry/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Merge-Sort-%EB%B3%91%ED%95%A9%EC%A0%95%EB%A0%AC-%ED%95%84%EC%88%98-%EA%B8%B0%EB%B3%B8-%EC%A0%95%EB%A6%AC-%EA%B5%AC%ED%98%84-split-merge-%EC%BD%94%EB%93%9C-%EC%84%A4%EB%AA%85-Kotlin
    
    #병합 정렬
    O(n log n)의 속도를 가진 정렬 알고리즘

    정렬된 List 2개를 정렬된 List하나로 합치는 과정을 O(N+M)에 수행 가능할때 성립
    List에 있는 원소들을 나눌 수 없을 때 까지 나누고, 합쳐가며 정렬되는 구조
    - 나누는 함수(split), 합치는 함수(merge)가 필요

    1. 2개 미만의 List는 정렬되었다고 정의
    병합정렬은 이 특성을 이용해, 더 이상 나눌 수 없을 때 까지 List를 나눔
    즉, List당 원소가 하나만 남을때 까지 나눔

    2. 병합된 2개의 List를 합침

    #병합 정렬 특징
    - 어떠한 상황에서도 O(n log n)의 준수한 속도를 낼 수 있음
    - 기존의 데이터를 담을 추가적인 배열 공간이 필요

    #백준 문제 증명
    - 백준 2751
    https://www.acmicpc.net/problem/2751

*/

fun main() {
    BaseAlgorithmMergeSortPractice().solve()
}

class BaseAlgorithmMergeSort {
    fun solve() {
        // val a = listOf(2, 6, 4, 10, 8, 9, 3, 1, 5, 7)
        // println("${a.mergeSort()}")

        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        val arr = Array(n){0}
        repeat(n) {
            arr[it] = br.readLine().toInt()
        }

        val sortedArr = arr.toList().mergeSort()
        sortedArr.forEach {
            bw.write("$it\n")
        }
    
        bw.flush()
        bw.close()
        br.close()
    }

    // split 역할을 수행하는 함수
    fun <T : Comparable<T>> List<T>.mergeSort(): List<T> {
        if(this.size < 2) return this // baseCondition
        val middle = this.size / 2

        val left = this.subList(0, middle).mergeSort()
        val right = this.subList(middle, this.size).mergeSort()

        return merge(left, right)
    }

    // 병합 역할을 수행하는 함수
    private fun <T : Comparable<T>> merge(left: List<T>, right: List<T>): List<T> {
        var leftPointer = 0
        var rightPointer = 0

        val result = mutableListOf<T>()

        while(leftPointer < left.size && rightPointer < right.size){
            val leftE = left[leftPointer]
            val rightE = right[rightPointer]
            when {
                leftE < rightE -> result.add(left[leftPointer++])
                leftE > rightE -> result.add(right[rightPointer++])
                else -> {
                    result.add(right[rightPointer++])
                    result.add(left[leftPointer++])
                }
            }
        }
        if(leftPointer < left.size){
            result.addAll(left.subList(leftPointer, left.size))
        }
        if(rightPointer < right.size){
            result.addAll(right.subList(rightPointer, right.size))
        }
        return result
    }
}

// 직접 구현
class BaseAlgorithmMergeSortPractice {

    fun solve() {
        val a = listOf(2, 6, 4, 10, 8, 9, 3, 1, 5, 7)
        println("${a.mergeSort()}")

        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()
        val arr = Array(n){0}
        repeat(n) {
            arr[it] = br.readLine().toInt()
        }

        val sortedArr = arr.toList().mergeSort()
        sortedArr.forEach {
            bw.write("$it\n")
        }
    
        bw.flush()
        bw.close()
        br.close()
    }

    fun <T : Comparable<T>> List<T>.mergeSort(): List<T> {
        if(this.size < 2) return this // base condition
        val middle = this.size / 2

        val left = this.subList(0, middle).mergeSort()
        val right = this.subList(middle, this.size).mergeSort()

        return merge(left, right)
    }

    private fun <T : Comparable<T>> merge(left: List<T>, right: List<T>): List<T> {
        var lp = 0
        var rp = 0
        val result = mutableListOf<T>()

        while(lp < left.size && rp < right.size) {
            when {
                left[lp] < right[rp] -> result.add(left[lp++])
                left[lp] > right[rp] -> result.add(right[rp++])
                else -> {
                    result.add(left[lp++])
                    result.add(right[rp++])
                }
            }
        }
        if(lp < left.size) {
            result.addAll(left.subList(lp, left.size))
        }
        if(rp < right.size){
            result.addAll(right.subList(rp, right.size))
        }

        return result
    }
}