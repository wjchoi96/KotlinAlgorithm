/*
    백준 단계별로 풀어보기 투포인터 유형
    gold 5
    스페셜 저지

    두 용액

    산성 용액, 알칼리성 용액을 보유
    각 용액은 하나의 정수가 부여되어있다

    1<= 산성 <= 1,000,000,000
    -1<= 알칼리 <= -1,000,000,000

    두 용액을 혼합한 용액의 특성값은 두 용액의 합으로 정의
    두 용액을 혼합해 0에 가까운 용액을 만드려고 한다

    두 종류의 알칼리성 용액, 혹은 두종류의 산성용액만으로도 특성값이 0에 가까운 용액을 만드는 경우도 존재

*/
/*
    2 <= n <= 100,000
*/
/*
    1. 정렬
    2. start = 0, end = n-1
    3. start + end 의 결과값에 따라 포인터 이동
    - sum < 0 => start++
    - sum > 0 => end++
    - sum == 0 => 정답
    
    4. 종료조건
    -1. 이전값이 현재값보다 0에서 멀어지면 종료
    -2. start == end 
*/
/*
    제출
    1. 성공
*/
fun main(args : Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   

    val n = br.readLine().toInt()
    val arr = br.readLine().split(' ').map{it.toInt()}.toTypedArray()
    arr.sort()
    print("${arr.toList()}\n")

    var start = 0
    var end = n-1
    var min = Int.MAX_VALUE
    var sum : Int
    var resStart = 0
    var resEnd = n-1
    while(start != end){
        sum = arr[start] + arr[end]
        if(Math.abs(sum-0) < min){
            min = Math.abs(sum-0)
            resStart = start
            resEnd = end
        }
        print("a[$start] + a[$end] = $sum, min : $min\n")
        when {
            sum > 0 -> end--
            sum < 0 -> start++
            sum == 0 -> {
                resStart = start
                resEnd = end
                break
            }
        }
    }
    bw.write("${arr[resStart]} ${arr[resEnd]}\n")

    bw.flush()
    bw.close()
    br.close()
}