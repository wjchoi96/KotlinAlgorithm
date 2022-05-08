/*
    바킹독님 알고리즘 강의 중 배열단원 연습문제2

    바킹독님이 제시한 문제로 해당 문제를 O(n)으로 푸는 방법을 고민

    주어진 길이 N의 int 배열 arr에서 합이 100인 서로 다른 위치의 두 원소가 존재하면 1을
    존재하지 않으면 0을 반환하는 함수 func2(arr : Array<Int>, n : Int) 를 작성
    arr의 각 수는 0 이상 100 이하이고 n은 1000이하이다
*/
/*
    input
    [1, 52, 48], 3
    output
    1

    input
    [50, 42], 2
    output
    0

    input
    [4, 13, 63, 87], 4
    output
    1
*/
/*
    O(n^2) 방식과, O(n) 방식이 존재
*/

fun main(args : Array<String>){
    val arr1 = arrayOf(
        1, 52, 48
    )
    val arr2 = arrayOf(
        50, 42
    )
    val arr3 = arrayOf(
        4, 13, 63, 87
    )
    println("O(n^2) : ${solveBigODoubleN(arr1, arr1.size)}, O(n) : ${solveBigON(arr1, arr1.size)}")
    println("O(n^2) : ${solveBigODoubleN(arr2, arr2.size)}, O(n) : ${solveBigON(arr2, arr2.size)}")
    println("O(n^2) : ${solveBigODoubleN(arr3, arr3.size)}, O(n) : ${solveBigON(arr3, arr3.size)}")
}

// O(n^2)
private fun solveBigODoubleN(arr : Array<Int>, n : Int) : Int {
    for(i in 0 until n){
        for(j in i+1 until n){
            if(arr[i]+arr[j] == 100){
                return 1
            }
        }
    }
    return 0
}

/*
    size가 101인 tempArr을 생성해준다 (0~100)
    arr배열을 순회하면서 현재 원소를 idx로 tempArr 배열에 접근해 ++해준다
    100-arr[i] 의 값을 idx로 tempArr배열에 접근해 해당 값이 존재하나 확인해본다
    존재한다면 1 리턴
*/
//O(n)
private fun solveBigON(arr : Array<Int>, n : Int) : Int {
    val tempArr = Array(101){0}
    for(i in 0 until n){
         // 자기 자신을 체크하는 문제를 발견하여, 자기 자신은 포함시키지 않음
         // => if(100-arr[i] != arr[i] && tempArr[100-arr[i]] != 0){
         // 자기 자신인지 체크하지말고, tempArr을 먼저 체크 한 후에 자기 자신을 ++ 해주는것으로 변경
        if(tempArr[100-arr[i]] != 0){
            return 1
        }
        tempArr[arr[i]]++
    }
    return 0
}