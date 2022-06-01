/*
    바킹독님 알고리즘 강의 중 수학단원 최대공약수

    최대공약수
    - Greatest Common Divisor
    - GCD
    - 두 수의 약수 중 제일 큰 수

    #약수
    어떤 수를 나누어떨어지게 하는 수

    #약수 구하기
    1. 1..n 을 순회하며 n을 나눠떨어지게 하는 수의 목록
    : O(n)

    2. 루트n보다 작은 약수만 구하고, 해당 약수들로 n을 나눈 몫들을 구한다
    - 약수끼리 곱이 n이 되게끔 짝을 지을 수 있다
        예) 18 = [1,2,3,6,8,18]
        => [1,18], [2,9], [3,6]
        => 1,2,3만 구하면 6,9,18은 자동으로 따라온다
    - 루트n보다 작은 약수만 구하고, 해당 약수들로 n을 나눈 몫들을 구한다
    - 다만, 16과 같은 경우들은 예외처리가 필요하다
        에) 16 = [1,2,4,8,16]
        => 예외처리를 해주지 않으면 4가 빠지거나, 4가 2번 들어가게 된다
    
    #최대공약수 구하기 
    - 일반적으론 두 수 의 약수 목록을 다 뽑아내고 정렬을 수행
    - 효율적인 유클리드 호제법

    #유클리드 호제법
    두 수 A,B에 대해 A를 B로 나눈 나머지를 r이라고 한다면
    GCD(A,B) = GCD(B,r) 이다
        예)
        GCD(20, 12) = GCD(12, 8), GCD(8, 4) = GCD(4, 0) = 4
    - O(log(max(a,b)))

    #최소공배수
    Least Common Multiple
    LCM
    a*b = gcd(a, b) * lcm(a, b) 이 성립

    #연립합동방정식
    : getPersonNum()
    1. 6명씩 조를 짰을때 3명이 남는다
    2. 5명씩 조를 짰을때 2명이 남는다
    3. 30명 미만이다
    사람의 수를 구하시오

    => 30미만의 6으로 나눴을때 3이남고, 5로 나눴을때 2가 남는 수를 구하시오
    30 미만의 수 중에
    - 6으로 나눴을떄 나머지가 3인 수의 목록을 구한다
    - 해당 목록에서 5로 나눴을때 나머지가 2인 수를 구한다
    
    연습문제
    1. 백준 6064(solve)
    - 연립합동방정식 연습문제

*/

fun main(args: Array<String>){
    val n = 16
    val div1 = commonDivider1(n)
    println(div1)

    val div2 = commonDivider2(n)
    println(div2)

    println("${gcd(20, 12)}")
    println("${gcd2(20, 12)}")

    getPersonNum()
}

//연립합동방정식 예시 문제
private fun getPersonNum(){
    // 6으로 나눴을때 나머지가 3인 수들
    for(i in 3 until 30 step(6)){
        if(i%5==2) println("$i")
    }
}


// 최소공배수 구하기
private fun lcm(a: Int, b: Int): Int {
    val gcd = gcd(a,b)
    // overflow를 방지하기 위해 *보다 /를 먼저 수행
    return a/gcd*b
}

// 최대공약수 구하기. 유클리드 호제법 반복 O(log(max(a,b)))
private fun gcd2(x: Int, y: Int): Int {
    var a = x
    var b = y
    while(a%b!=0){
        var r = a%b
        a = b
        b = r
    }
    return b
}


// 최대공약수 구하기. 유클리드 호제법 재귀 O(log(max(a,b)))
private fun gcd(a: Int, b: Int): Int {
    if(b==0) return a
    return gcd(b, a%b)
}

// 2. 약수 구하기. 시간복잡도 O(루트N)
private fun commonDivider2(n: Int): List<Int>{
    val div = ArrayList<Int>()
    for(i in 1..Math.sqrt(n.toDouble()).toInt()){
        if(n%i==0) div.add(i)
    }
    var size = div.size
    for(i in size-1 downTo 0){
        if(n/div[i] == div[i]) continue
        div.add(n/div[i])
    }
    return div
}

// 1. 약수 구하기. 시간복잡도 O(n)
private fun commonDivider1(n: Int): List<Int>{
    val div = ArrayList<Int>()
    for(i in 1..n){
        if(n%i==0) div.add(i)
    }
    return div
}