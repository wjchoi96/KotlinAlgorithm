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

    
    연습문제
    1. 백준 1978(solve)

*/
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