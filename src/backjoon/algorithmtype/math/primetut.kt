/*
    #소수
    - 1과 자기 자신만으로 나누어지는 수
    => 약수가 2개인 수
    #합성수
    - 1과 자기 자신을 제외한 다른 약수를 가지고 있는 수
    - 소수의 반대

    !! 1은 소수도, 합성수도 아니다

    #소수 판정법
    1. 2부터 n-1의 수로 나누어 지지 않으면 소수이다
    2. 2부터 루트n까지의 수로 나누어 지지 않으면 소수이다
    합성수 n에서 1을 제외한 가장 작은 약수는 루트n 이하이다
        예) 
        n=18일때, 1이 아닌 가장 작은 약수는 2
        루트18 = 4.xx 이니, 2는 루트18 이하이다

        증명(n=21, x=3을 넣고 흐름을 따라가 보자)
        합성수n에서 1을 제외한 가장 작은 약수를 x
        n/x또한 1이 아닌 n의 약수이기때문제 x<=(n/x)
        우변의 분모 x를 좌변으로 옮기면 x^2<=n
        => x<=루트n
    
    #범위 내에서 소수 판정
    3. 에라토스테네스의 체
    - 범위 내 소수의 목록을 구할때 사용
    예)
    #조건
    1부터 n까지 소수의 목록을 구한다

    #준비
    1부터 n까지의 1차원 배열. 
    해당 칸의 소수일경우 true, 아닐경우 false
    초기값으로 1에 false, 나머지는 true를 준다

    #구현
    커서를 하나 둬서 2를 가리키게 한다 
    => 2부터 n까지 진행되며 소수를 찾은 후 어떠한 작업을 진행 할 예정
    1. 2의 배수인 다른 수들을 모두 false 로 만든다
    2. 커서를 다음칸으로 옮겨 3을 가리킨다
    3. 3의 배수를 false 로 만든다
    4. 커서를 다음칸으로 옮겨 4를 가리켰지만, false 처리되어 다음칸인 5를 가리킨다
    5. 5의 배수를 false 로 만든다
    => n까지 반복

    3_1. 에라토스테네스의 체 수학적으로 개선
    
    #조건
    현재 커서가 5를 가리킨 상황이고, 5의 배수들을 false로 만들 차례

    #개선
    5의 배수들을 살펴보면, 이미 합성수로 판정이 되어있는 값들이 많이 존재
    => 25, 25만 아직 true이고, 나머지는 이미 모두 false
    => 커서가 5까지 오는동안 커서는 2와 3을 지나쳤고 자연스럽게 5보다 작은 소인수를 가지는
    합성수는 이미 다 걸러진 상태

    5의 배수 중 25강조해서 살펴보면
    - 25보다 작은 5의 배수인 10(2*5), 15(3*5), 20(4*5) 들은 보다시피 5보다 더 작은
    소인수가 존재한다
    - 즉 커서거 5인 지금 5보다 작은 소인수를 가지는 5의 배수는 이미 false로 변경되어있다
    => 5^2 인 25부터 false로 바꿔나가는 작업을 수행하면 된다

    #사용
    n - m 내 소수를 구하는 문제가 나오면 그냥 1부터 m까지 에라토스테네스의 체를 돌리고
    n - m 을 순회하여 소수를 출력하면 된다


    #소인수 분해
    - 정수를 소수의 곱으로 나타내는 것
    => 1100 = 2*2*5*5*11

    #준비
    n: 1100, i: 2
    
    #구현
    1. n을 i로 나누어 떨어지지 않을 때 까지 나눈다
    나눈 i는 소인수 목록에 추가한다

    2. 더 나누어지지 않는다면 i 증가
    - 1번 반복

    3. n이 1이 될때까지 반복
        #해당 방법의 정당성
        1. 소인수 목록에 있는 수를 전부 곱하면 원래의 n이 되는가?
        - 당연
        2. 목록에 있는 수가 전부 소수인가?
        - 작은 소인수부터 나눠왔으니 맞다

    : 시간복잡도 O(n)으로 구현

    #개선
    - n을 i로 나누는 이유는 n에서 소인수 i를 빼내기 위함
    - i^2가 n보다 커지게되면 더이상 나눠보는 작업을 하지 않더라도
    n에는 루티n이하인 소인수가 없다, 다르게 말하면 n은 소수다 라는것을 알 수 있다
    => i를 나눠주다가 i^2가 n보다 커졌다면, i와 n사이의 수 중 n을 나눌 수는 없다는걸 알 수 있다
        예) 1100을 소인수 분해하다가 n:11, i:5
        5~10 에는 11을 나눌 수가 없다
        과정을 종료하고 n이 1이 아닌 경우 해당 수를 소인수 목록에 추가

    연습문제
    1. 백준 1978(solve)

    2. 백준 1929(solve)

*/
fun main(args: Array<String>){
    // val arr = arrayOf(
    //     1,2,3,4,5,6,7,8,9
    // )
    // val count = isPrime4(arr)
    // print("count: $count\n")

    val primes = primeFactorization2(1100)
    for(i in primes){
        print("${i}\n")
    }
}

// 소인수분해 2 개선 O(lgn)
private fun primeFactorization2(v: Int): List<Int>{
    var primeList = ArrayList<Int>()
    var n = v
    var i = 2
    while(n!=1){
        if(i*i>n){
            primeList.add(n)
            break
        }
        while(n%i==0){
            n /= i
            primeList.add(i)
        }
        i++
    }
    return primeList
}


// 소인수분해 1 O(n)
private fun primeFactorization(v: Int): List<Int>{
    var primeList = ArrayList<Int>()
    var n = v
    var i = 2
    while(n!=1){
        while(n%i==0){
            n /= i
            primeList.add(i)
        }
        i++
    }
    return primeList
}

// 에라스토테네스의 체 최종본?
private fun getPrimeState2(n: Int): Array<Boolean> {
    val state = Array(n+1){true}
    state[0]=false
    state[1]=false
    val x = Math.sqrt(n.toDouble()).toInt()
    for(i in 2..x){
        if(!state[i]) continue
        var j = i
        while(i*j<=n){
            state[i*j]=false
            j++
        }
    }
    return state
}

// 3_1번 판정법 에라스토테네스의 체 개선
// 1부터 n까지 범위에서 소수를 구하라
// O(nlglgn)
private fun isPrime4(arr: Array<Int>): Int{
    if(arr.isEmpty()) return 0
    var max = arr[arr.size-1]
    val state: Array<Boolean> = Array(arr.size+1){true}
    state[1] = false // 1 indexed 

    var i = 2
    while(i*i<=state.size){ //i^2가 n보다 커진다면 아무값도 변경하지 않기 때문
        if(!state[i]) {
            i++
            continue
        }
        var j = i // i^2 부터 실행
        while(i*j <= max){ 
            state[i*j] = false // 1부터 n이 아닌 모든 범위에 통용되고싶으면 해당 idx 접근을 수정해야 한다
            j++
        }
        i++
    }
    var count = 0
    for(k in 1 until state.size){ // 1 indexed 
        if(state[k]) {
            print("$k\n")
            count++
        }
    }
    return count
}


// 3번 판정법 에라스토테네스의 체
// 1부터 n까지 범위에서 소수를 구하라
// O(n^2)?
private fun isPrime3(arr: Array<Int>): Int{
    if(arr.isEmpty()) return 0
    var max = arr[arr.size-1]
    val state: Array<Boolean> = Array(arr.size+1){true}
    state[1] = false // 1 indexed 
    for(i in 2 until state.size){
        if(!state[i]) continue
        var j = 2
        while(i*j <= max){ 
            state[i*j] = false // 1부터 n이 아닌 모든 범위에 통용되고싶으면 해당 idx 접근을 수정해야 한다
            j++
        }
    }
    var count = 0
    for(i in 1 until state.size){ // 1 indexed 
        if(state[i]) {
            print("$i\n")
            count++
        }
    }
    return count
}

// 2번 판정법 O(루트n)
private fun isPrime2(n: Int): Boolean {
    if(n==1) return false
    var i = 2
    while(i*i<=n){
        if(n%i==0) return false
        i++
    }
    return true
}

// 1번 판정법 O(n)
private fun isPrime1(n: Int): Boolean {
    if(n==1) return false
    for(i in 2 until n){
        if(n%i==0)return false
    }
    return true
}