/*
    바킹독님 알고리즘 강의 중 merge sort 구현

    1. 정렬된 두 리스트를 하나로 merge하는것은 O(n+m)의 시간복잡도를 지닌다
    2. 원본 배열을 반으로 쪼개, 정렬 후 merge
    - 반으로 쪼갠 배열을 정렬하기 위해 또 반으로 쪼개, 정렬 후 merge
    # 귀납적 사고를 통해 길이가8인 배열을 정렬하기위해선, 4인배열, 2인배열, 1인배열을 정렬할 수 있어야 한다 => 가능
    # 재귀를 통해 구현

    바킹독님이 템플릿을 제시해주셨지만, 일단 먼저 구현을 시도해본다
    => 그냥 보자
    => java 알고리즘 책 p246 참고

    # 바킹독님은 buff에다가 merge 하고, merge가 끝나면 arr 에 buff의 값을 복사하는 방식으로 진행하셨다
    # 책은 매번 복사하지않고, start to mid 만 복사하고, buff(start to mid) 와 mid to end 를 원본에 병합하는 방식으로 진행
*/
private val arr = arrayOf(
    6, -8, 1, 12, 8, 15, 7, -7
)
private var totalTime = 0
private val buff = Array(arr.size){0}
fun main(args: Array<String>){
    
    mergeSort(0, arr.size)
    println("${arr.toList()}, o($totalTime)")
}
/*
    반으로 쪼개는 함수 => mergeSort
    합치는 함수 => merge

    0,8
    0,4 4,8
    0,2 2,4 4,6 6,8
    0,1 1,2
*/
private fun mergeSort(start: Int, end: Int){
    if(end-start == 1) return
    val mid = (start+end)/2
    mergeSort(start, mid)
    mergeSort(mid, end)
    merge(start, end)
}

/*
    0, 8
    0, 4
    0, 2
    0, 1 => return 되니, merge(0, 2)가 호출될것

    0~1 배열과 1~2 배열을 merge
    start = 0
    mid = 1
    end = 2

    0, 8
    4, 8
    6, 8
    7, 8

    6,7,8 
*/
private fun merge(start: Int, end: Int){
    val mid = (start+end)/2
    println("start[$start], mid[$mid], end[$end]")
    print("[")
    for(i in start until mid){
        print("${arr[i]} ")
    }
    print("]")

    print(" [")
    for(i in mid until end){
        print("${arr[i]} ")
    }
    print("]\n")

    // start until mid 배열, mid until end 배열 merge
    // 최소 0 until 1, 1 until 2 단위로 실행
    // merge에 옮기는게 아니다, merge를 이용해서 본래 배열에 적용하는것
    // start to min 배열을 buff에 복사하고, buff와, mid to end 배열을 체크하며 원본에 병합 결과를 적용

    var buffSize = start
    for(i in start until mid){
        buff[buffSize++] = arr[i] // start until mid 값을 buff 에 복사
    }
    println("o(${mid-start}) for copy start to mid")

    var i = mid  
    var buffIdx = start
    var mergeIdx = start
    // buff(start to mid)와, mid to end 배열을 체크하며 원본에 병합 결과를 적용
    while(i < end && buffIdx < buffSize){
        arr[mergeIdx++] = if(buff[buffIdx] <= arr[i]) buff[buffIdx++] else arr[i++]
    }
    while(i<end) arr[mergeIdx++] = arr[i++]
    while(buffIdx<buffSize) arr[mergeIdx++] = buff[buffIdx++]

    println("o(${mergeIdx-start}) for merge two arr")

    print("[")
    for(x in 0 until mergeIdx){
        print("${arr[x]} ")
    }
    println("] => o(${mid-start+mergeIdx-start})")
    totalTime += (mid-start+mergeIdx-start)
}