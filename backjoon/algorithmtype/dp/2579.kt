/*
    바킹독님 알고리즘 강의 중 dp단원 연습문제
    sliver3

    계단 오르기

    계단 오르기 게임은 계단 아래부터 계단 꼭대기에 위치한 도착점까지 가는 게임
    계단을 밟으면 해당 계단의 점수를 얻게 된다

    계단을 오르는 규칙
    1. 한번에 한계단 또는 두계단씩 오를 수 있다
    2. 연속된 세개의 계단을 모두 밟아서는 안된다. 단, 시작점은 계단에 포함되지 않는다
    - 근데 1,2,3을 연속으로 밟을 수 없다네
    - 혹시 1번계단 안밟아도 되는거니?
    => 시작점과 1번 계단은 다르다
    => 한번에 한개 or 두개를 오를 수 있다고 되어있으니 시작점에서 1번 or 2번을 선택 가능한거였다
    3. 마지막 도착 계단은 반드시 밟아야 한다

    이 게임에서 얻을 수 있는 총 점수의 최대값을 구하시오

    1<= n <=300
    점수는 10000 이하의 자연수

    300*10000 => 3000000 
    
    1초
*/
/*
    예)
    arr = [10, 20, 15, 25, 10, 25], n = 6

    1. 테이블 구하기
    dp[n]: 해당 계단이 마지막 계단일때, 얻을 수 있는 점수의 최대
    2. 점화식 찾기
    dp[1]: 10
    dp[2]: 30
    dp[3]: 40
    dp[4]: 55 => max(10+20+25, 10+15+25) => max(dp[2]+this, dp[1]+dp[3]+this)
    => dp[3]을 사용할때 1,3 의 경우여야만 한다
    => dp[3]이 1,2,3 의 경우라면 1,2,3,4 계단을 연속해서 밟을 수 없기 때문
    
    dp[5]: => 1,2,3,5 1,2,4,5
    => dp[4]를 구할땐 1,2,4 의 경우여야만 한다
    => dp[4]가 1,3,4 라면 1,3,4,5 의 계단을 밟을 수 없기 때문
    => dp[4]는 1,2,4 / 1,3,4 두가지가 있을 수 있는거같은데?

    dp[6]
    => dp[5]+this, dp[4]+this
    => dp[5]는 dp[2]+arr[3] or dp[2]+arr[4]

    dp[n] = dp[n-1]+this, dp[n-2]+this
    인데, dp[n-1]을 구하려면 dp[n-3]+arr[n-2] 이여야 할것이다

    #dp[n] = max(dp[n-2]+arr[n], dp[n-3]+arr[n-2]+arr[n])

    3. 초기값 정하기
    n-3 까지 가기때문에 최소 3개의 초기값이 필요하다. 0은 제외
    dp[1] = arr[1]
    dp[2] = arr[1] + arr[2]
    dp[3] = arr[1] + arr[2] + arr[3]
*/
/*
    제출
    1. 틀렸습니다(3% 였나)
    - 초기값 문제
    - 첫번쨰 계단은 계단에 포함안한다면서 1,2,3은 안된다네

    2. 틀렸습니다(15%)
    - 1번 계단을 안밟아도 된다고?

    3. 런타임에러(90% 넘었던듯): ArrayIndexOutOfBounds
    - init dp 사이즈 체크 오류

    4. 성공
    - 첫번째 계단은 계단으로 포함안한다면서 1,2,3 은 못밟는 거 => 시작점은 계단에 포함 안함이였네;
    - 첫번째 계단을 안밟아도 된다는거
    - 문제 조건이 애매한거 아닌가 이건
*/

private lateinit var dp: Array<Int>
private lateinit var arr: Array<Int>
fun main(args: Array<String>){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    arr = Array(n+1){0}
    repeat(n){ arr[it+1] = br.readLine().toInt() }
    initDp(n+1)
    bw.write("${getMaxScore(n)}\n")
    
    bw.flush()
    br.close()
    bw.close()
}

private fun initDp(size: Int){
    dp = Array(size){-1}
    dp[0] = 0
    dp[1] = dp[0] + arr[1]
    if(size>2)dp[2] = dp[1]+arr[2]
}
private fun getMaxScore(x: Int): Int{
    if(dp[x] != -1){
        println("dp[$x]: ${dp[x]}")
        return dp[x]
    }
    dp[x] = Math.max(
        getMaxScore(x-2),
        getMaxScore(x-3) + arr[x-1]
    ) + arr[x]
    return dp[x]
}
/*
6
123
14
3
2
13
4

답: 143

    123 14 3 2 13 4

    dp[1] = 123
    dp[2] = 137
    dp[3] = 140
    dp[4] = 139 => max(dp[2], dp[3]) + this
    : dp[3] => dp[1]+arr[2] or dp[1]+arr[3]
    : 1,2,4 or 1,3,4

    dp[5]
    : dp[3]+this op dp[2]+4번째칸+this
    : 


5
1
4
3
1
9
답 16

    1 4 3 1 9

    dp[1]: 1
    dp[2]: 5
    dp[3]: 4
    dp[4]: 6
    : dp[2]+this, dp[1]+arr[3]+this
    
    dp[5]
    : 1,2,4,5 / 1,2,4,5
    : dp[3]+this, dp[2]+arr[4]+this
    : 4+9, 5+1+9
    : 13, 15
    
*/