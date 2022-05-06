/*
    백준 단계별로 풀어보기 투포인터 유형
    gold3

    소수의 연속합

    하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다
    3 : 3(한가지)
    41 : 2 + 3 + 5 + 7 + 11 + 13 = 11+ 13 + 17 = 41 (3가지)
    53 : 5 + 6 + 11 + 13 + 17 = 53 (2가지)

    연속된 소수여야 하고
    한 소수는 한번만 덧셈에 사용될 수 있다

    자연수를 주었을때, 해당 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 출력
*/
/*
    1<= n <= 4,000,000
    2초
*/
/*
    n이하의 소수 배열을 구한다 => 소수찾기 알고리즘 검색을 통해 시간복잡도가 낮은것을 파악
    => 에라스토스테네스의 체 https://st-lab.tistory.com/81
    => 그냥 logn 이하의 수를 검사하는 방식으로 적용
    
    정렬되어서 소수 리스트가 나온다

    start = 0
    end = 0
    sum = 0

    조건을 만족하면 sum -= arr[sum++]
    조건을 불만족하면 sum += arr[end++]

    종료조건
    end == n && start == n

    or 
    start 증가 조건에 end == n을 추가하고
    반복문 종료조건은 start만 체크
*/
/*
    제출

    1. 성공
    - 시간복잡도를 내리려면 소수판별 알고리즘을 에라스토스테네스의 체로 변경하면 좀 더 낮출 수 있을것
*/

fun main(args : Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()   

    val n = br.readLine().toInt()
    val primeList = getPrimeList(n)
    print("$primeList\n")

    var start = 0
    var end = 0
    var sum = 0
    var count = 0
    val size = primeList.size
    while(start<size){
        print("[$start to $end] : $sum\n")
        //n보다 커지면 뒤의값은 더해볼 필요가 없다
        if(sum >= n || end == size){
            if(sum==n){
                print("catch [$start to $end] : $sum\n")
                count++
            }
            sum -= primeList[start++]
        }else if(sum != n){
            sum += primeList[end++]
        }
    }
    bw.write("$count\n")

    bw.flush()
    bw.close()
    br.close()
}
private fun getPrimeList(n : Int) : List<Int> {
    val primeList : ArrayList<Int> = ArrayList()
    for(i in 2 until n+1){
        if(isPrime(i)){
            primeList.add(i)
        }
    }
    return primeList
}

private fun isPrime(n : Int) : Boolean {
    for(i in 2 until Math.sqrt(n.toDouble()).toInt()+1){ // toInt로 인해 소수점 뒤의값이 다 깎이기 때문에 원래보다 범위가 줄어든다. +1을 통해 toInt된 제곱근 값을 포함하도록 하자
        if(n%i == 0){
            return false
        }
    }
    return true
}
