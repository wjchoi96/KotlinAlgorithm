/*
    merge sort 구현 코드가 정확한지 체크하기 위한 정렬문제
    백준 2751
    sliver 5

    merge sort 구현 코드를 사용하여 수행
*/

private lateinit var arr : Array<Int>
private lateinit var buff : Array<Int>
fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val n = br.readLine().toInt()
    arr = Array(n){0}
    buff = Array(n){0}
    repeat(n){
        arr[it] = br.readLine().toInt()
    }
    mergeSort(0, n)

    for(i in 0 until n){
        bw.write("${arr[i]}\n")
    }

    bw.flush()
    bw.close()
    br.close()
}
private fun mergeSort(start: Int, end: Int){
    if(end-start == 1) return
    val mid = (start+end)/2
    mergeSort(start, mid)
    mergeSort(mid, end)
    merge(start, end)
}

private fun merge(start: Int, end: Int){
    val mid = (start+end)/2
    var buffSize = start
    for(i in start until mid){
        buff[buffSize++] = arr[i] // start until mid 값을 buff 에 복사
    }

    var i = mid  
    var buffIdx = start
    var mergeIdx = start
    // buff(start to mid)와, mid to end 배열을 체크하며 원본에 병합 결과를 적용
    while(i < end && buffIdx < buffSize){
        arr[mergeIdx++] = if(buff[buffIdx] <= arr[i]) buff[buffIdx++] else arr[i++]
    }
    while(i<end) arr[mergeIdx++] = arr[i++]
    while(buffIdx<buffSize) arr[mergeIdx++] = buff[buffIdx++]
}