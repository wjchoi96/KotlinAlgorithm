/*
    바킹독님 알고리즘 강의 중 수학단원 연립합동방정식 연습문제
    sliver 1

    카잉 달력
    
    카잉제국의 달력은 M과 N보다 작거나 같은 두개의 자연수 x,y를 가지고 각 년도를 <x:y>의 형식으로 표현
    첫번째 해를 <1:1>
    두번째 해를 <2:2> 
    로 표현하였다

    <x:y>의 다음 해를 포현한 것을 <x`:y`>라고 한다면
    만일 x<M 이라면
    x` = x+1 
    아니라면
    x` = 1

    만일 y<N 이라면
    y` = y+1
    아니라면
    y` = 1

    <M:N>은 달력의 마지막 해로써, 세상이 종말한다고 예언지 전해진다

    예)
        M=10, N=12
        첫번째해: <1:1>
        11번째해: <1:11>
        13번쨰해: <3:1>
        60번째해: <10:12> (마지막해)

    4개의 정수 M,N 그리고 x,y가 주어질때
    <x:y>는 몇번째 해인지 구하라

    <x:y>가 유효하지 않은 값이라면 -1을 출력

    1초
    1<= M,N <= 40,000
    1<= x <= M
    1<= y <= N
*/
/*
    <1:1>
    <2:2>
    <3:3>
    ...
    <10, 10> : 10번째해
    <1, 11> : 11번째
    <2, 12>
    <3, 1>
    <4, 2>
    <5, 3> : 5인지 15인지 어떻게 구분하지
    ...
    <10:12>
*/
/*
    x의 값만 고려한다면 
    만일 M이 10이라면
    x가 3일때 3번째 해인지, 13번째 해인지 구분 할 수 없다

    y도 마찬가지

    M이 10, N이 12일때
    <3,3> 은 
    x만을 고려하면 3, 23, 33.. 이고
    y만을 고려한다면 3, 15, 27... 이다

    마지막해는 N*M/2 ?
    N과 M의 조합인데, 서로 수를 바꾸는 경우르 제외해서 /2
    = max라 칭함
    => 틀렸다

    max는 N과 M의 최소공배수

    <x:y> 
    max를 M으로 나눠서 x가 남고
    max를 N으로 나눠서 y가 남는
    max 이하의 수 이다

*/
/*
    제출
    1. 틀렸습니다(0%)
    - res 기본값 오타(-11 to -1)

    2. 틀렸습니다(6%)
    - 마지막 해가 입력되는 경우를 처리하지 못함

    3. 틀렸습니다(6%)
    - x,y가 n,m과 같은 수라면 0으로 취급해서 로직에 넣어야 한다

    4. 성공
    - 풀고나서 바킹독님 코드를 확인해보니 나와 똑같다

*/
/*
    개선
    https://www.acmicpc.net/source/10890923
    크게 다르지않아보이는데 훨씬 깔끔하다

*/

/*
    생각해볼점
    1. 코드에서 i<=max로 for문을 돌리는데, 이때 max값이 lcm이 아닌 n*m으로 설정하고 반복문을 돌려도 잘 작동한다
    그 이유는?
    => n*m으로 설정해도 만족한다면 lcm에서 종료될것이기 때문?

    2. 식이 2개인 경우를 해결했는데, 식이 3개, 4개가 된다면 어떻게 될까?
    예를 들어 <x:y:z> 꼴이 되어서 K로 나눈 나머지가 z여야 한다는 조건이 추가된 상황
    식이 2개일때는 lcm(n,m)/m 개의 수, 즉 최대 n개의 수를 확인
    3개일때는 몇개의 수를 확인해야할까?
    - NK개의 수로 착각할 수 있는다 답은 N+K개. 그 이유는?
    - x,y,z의 최소공배수를 구한뒤..흠

*/

fun main(args: Array<String>){
    Solution6064().solve()
}
class Solution6064 {
    fun solve(){
        val br = System.`in`.bufferedReader()
        val bw = System.out.bufferedWriter()
        
        repeat(br.readLine().toInt()){
            val input = br.readLine().split(' ').map{it.toInt()}
            val res = getCaingCal(input[0], input[1], input[2], input[3])
            bw.write("$res\n")
        }

        bw.flush()
        br.close()
        bw.close()
    }
    private fun getCaingCal(m: Int, n: Int, a: Int, b: Int): Int{
        val max = lcm(m, n)
        var x = if(a==m) 0 else a
        var y = if(b==n) 0 else b
        var res = -1
        for(i in x..max step(m)){
            if(i%n == y) res = i
            // if((i-y)%n==0) res = i // 이게 개선코드의 핵심
        }
        return res
    }
    // 바킹독님의 약간 더 개선
    // 예외를 하나 더 처리해주는대신, 순환이 더 빨리 종료될 수 있다
    private fun getCaingCal3(m: Int, n: Int, a: Int, b: Int): Int{
        val max = lcm(m, n)
        var x = if(a==m) 0 else a
        var y = if(b==n) 0 else b
        for(i in x..max step(m)){
            if(i==0) continue // x==m, y==n 일때 max가 아닌 0을 리턴하는것을 방지
            if(i%n == y) return i // 발견하면 바로 리턴해서 순회를 종료
        }
        return -1
    }
    private fun gcd(x: Int, y: Int): Int{
        if(y==0) return x
        return gcd(y, x%y)
    }
    private fun lcm(x: Int, y: Int): Int{
        return x/gcd(x, y)*y
    }

    private fun getCaingCal2(m: Int, n: Int, x: Int, y: Int): Int{
        val max = lcm(m, n)
        var k = x
        do {
            //k-y를 나누는 이유?? (k%n==y) 를 체크해도 똑같지않나
            //내가 못피한 반례를 어떻게 적용시키는거지? 똑같아보이는데
            //꼭 나머지가 맞아떨어져야하는게 아니라, 나눠떨어지기만 하면되는건가?
            if((k-y)%n==0){ // k-y 를 n으로 나눴을때 나눠떨어지면 중단
                break
            }
            k += m // m이하 나머지가 x인 수들을 순회
        }while(k<=max) // k가 마지막해늘 넘는다면 반복 종료

        // 최대해를 넘기전에 구하지 못했다면 -1
        return if(k<=max)k else -1
    }
    /*
        10 12 3 9

        k는 3, 13, 23, 33 반복
        3-9 = -6
        13-9 = 5
        23-9 = 14
        33-9 = 26

        26$12 == 0
        33%12 == 9

        둘다 만족은 하는데, x,y 가 n,m과 같은 수일때를 고려하기 위해선
        (k-y)%n == 0 으로 하거나
        x,y가 n,m과 같을때는 0으로 취급해야한다
    */
}

/*
    반례

1
1 1 1 1

정답: 1
출력: -1

1
10 12 10 12

정답: 60
출력: -1

1
39999 2 39998 2

답: 39998
출력: -1

1
11 2 10 2

max: 22
답: 10
출력: -1

22에서 11로 나눴을때 나머지가 10?
=> 10

<1:1>
<2:2>
<3:1>
<4:2>
<5:1>
<6:2>
<7:1>
<8:2>
<9:1>
<10:2> : 10번째

1
11 2 11 2


*/