/*
    바킹독님 알고리즘 강의 중 정렬단원 정렬 응용문제
    sliver 4
    
    카드

    숫자 카드 n장을 가지고 있다
    적여있는 수는 -2^26 보다 크고, 2^26보다 작다

    가진 카드 중 가장 많이 가지고 있는 정수를 구하라
    가장 많이 가진 정수가 여러개라면 그중 작은것을 출력
*/
/*
    1<= n <= 100,000
    1초
*/
/*
    map 을 사용하면 될것같지만, 정렬단원 문제이니 map을 사용안하고 접근해야한다
    
    바킹독님 접근방식을 보며 생각해본다
    1. 정렬
    2. 정렬을 진행하면 같은 숫자들은 연속해서 위치하게 될것
    3. 연속되는 숫자들의 개수를 세고, maxCount, maxValue를 갱신해가면서 배열을 순회한다
*/
/*
    제출
    1. 런타임에러(numberFormat)
    - int형 범위 초과;

    2. 맞았습니다
    - 다만 공통된 코드가 존재하는게 맘에 안든다

    3. 맞았습니다
    - 공통된 코드를 하나로 합침
*/
/*
    반례에상
    1. 모든 입력값이 같은값이라면 현재 코드로 통과 못한다
    2. 제일 큰 값이 제일 많다면 현재코드로는 반영이 안된다
*/
fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    val arr = Array<Long>(n){0}
    repeat(n){
        arr[it]=br.readLine().toLong()
    }
    arr.sort()

    var maxValue: Long = 0
    var maxCount = 0
    var value: Long = 0
    var count = 0
    arr.forEach {
        if(it==value){
            count++
        }else {
            count = 1
        }
        value = it
        if(count>maxCount){
            maxCount = count
            maxValue = value
        }
    }

    bw.write("$maxValue\n")

    bw.flush()
    bw.close()
    br.close()
}