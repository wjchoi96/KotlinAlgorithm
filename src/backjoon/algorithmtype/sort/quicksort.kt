/*
    바킹독님 알고리즘 강의 중 정렬단원 퀵소트 구현 코드

    책의 경우는 pivot을 포함하여 배열을 체크하였고,
    바킹독님의 경우는 pivot을 제외하고 배열을 체크, 맨마지막에 pivot과 right를 swap해줬다

    바킹독님 코드가 더 간단하고 이해하기 편하다
    책은 pivot을 포함한 그룹, 안한그룹 / pivot보다 작은그룹, 큰그룹 등 좀 더 복잡하다
*/

private val arr = arrayOf(
    6, -8, 1, 12, 8, 15, 7, -7
)
private var totalTime = 0
private val buff = Array(arr.size){0}
fun main(args: Array<String>){
    
    quickSort(0, arr.size-1)
    println("${arr.toList()}")
}

private fun quickSort(start: Int, end: Int){
    if(end-start <= 1){ // 길이가 1이되면 base condition
        return
    }
    val pivot = arr[start] // pivot을 start item으로 두고
    var left = start+1 // pivot을 제외하고 시작한다
    var right = end
    while(true){
        //stayble sort를 지원안한다, pivot과 일치하는 값도 옮겨줘야한다 => pivot과 일치하는 값은 pivot 이상의 그룹으로 보낸다
        //pivot 이상, 이하의 그룹
        while(left<=right && arr[left]<=pivot)left++
        while(left<=right && arr[right]>pivot)right--
        if(left>right) break
        swap(left, right)
    }
    swap(start, right) // 마지막에 pivot과 right를 swap

    quickSort(start, right)
    quickSort(right+1, end)
}
private fun swap(idx1: Int, idx2: Int){
    val t = arr[idx1]
    arr[idx1] = arr[idx2]
    arr[idx2] = t
}