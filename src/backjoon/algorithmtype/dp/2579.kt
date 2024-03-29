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
    
    => 문제의 조건을 다시 이해해서 변경
    1. 첫번째 계단이 아닌 시작점을 계단으로 포함 안한다
    2. 시작점에서 한칸 또는 두칸을 이동해서 시작 가능 => 첫번째 계단을 안밟아도 된다
    dp[0] = 0 // 시작점
    dp[1] = dp[0]+arr[1]
    dp[2] = dp[1]+arr[2]
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
/*
    solve1
    : 이전에 풀었던 방식이 기억나서 해당 방식으로 진행 => 1차원 dp
    : 재귀방식(top-down), for문 방식(bottom-top) 모두 구현
    : getMaxScore, getMaxScore2

    바킹독님 방식
    : 2차원 dp를 사용하셔서 접근하신다
    : dp[n][c]
    => 현재까지 c개의 계단을 연속해서 밟고 n계단에 올라왔을때의 최대 점수(n계단은 반드시 밟아야 한다)
*/
/*
    바킹독님 방식
    solveBarkingDog
    1. 테이블 정의
    dp[n][c]
    => 현재까지 c개의 계단을 연속해서 밟고 n계단에 올라왔을때의 최대 점수(n계단은 반드시 밟아야 한다)
    
    2. 점화식 찾기
    => dp[n][1] = max(dp[n-2][2], dp[n-2][1]) + this
    => dp[n][2] = dp[n-1][1] + this

    3. 초기값 설정
    dp[0] = 0
    dp[1][1] = arr[1]
    dp[2][1] = arr[2]
    dp[2][2] = dp[1][1]+arr[2]

    dp[1][2] 의 경우는 없는 경우이기때문에 초기값 -1로 그대로 둔다. => 그러면 base condition에 걸리니 0으로 설정
    어차피 max 값을 사용하기때문에 걸러진다

    제출
    1. 런타임에러(100%): ArrayIndexOutOfBounds
    - init dp size 설정 누락

    2. 성공
    - top-bottom 

    3. 성공
    - bottom-top
*/
/*
    새로운 방식(바킹독님이 제시하신 새로운 풀이방식. 테이블 정의까지만 주어지고 혼자 접근해보자)
    solve2
    
    1. 테이블 정의
    dp[n]: n번째 계단까지 올라섰을때, 밟지 않을 계단의 합의 최소값
    n번째 계단은 반드시 밟지 않을 계단으로 선택해야 한다

    2. 점화식
    n번째 계단을 밟지 않았다면, n-1 계단은 무조건 밟아야한다
    n-2, n-3중 하나는 무조건 밟아야한다

    dp[0] = 0
    dp[1] = arr[1] // 자기 자신만 안 밟는다
    dp[2] = arr[2] // 자기 자신만 안 밟는다
    dp[3] = arr[3] // 자기 자신만 안 밟는다 => 이게 최소값이기 때문
    dp[4] = min(dp[1], dp[2]) + this
    : 0,2,3 / 0,1,3
    
    dp[n] = min(dp[n-3], dp[n-2]) + arr[n]

    3. 초기값
    n-3 까지 가야하니, 최소 3개는 채워져있어야한다
    dp[0] = 0
    dp[1] = arr[1]
    dp[2] = arr[2]
    dp[3] = dp[1] + this

    4. 출력값
    : dp는 n번째 계단까지 올라섰을때, 밟지 않을 계단의 합의 최소값. n번째 계단은 밟히면 안된다
    n번째 계단을 밟았을때 점수의 최대값을 구해야하니
    n-2계단을 밟지않고 n-2까지 왔을때 안밟은 계단 점수의 최소값,
    n-1계단을 밟지않고 n-1까지 왔을때 안밟은 계단 점수의 최소값 중 작은것을 max에서 뺀다

    제출
    1. 틀렸습니다(15%)
    - 바킹독님 풀이를 좀더 확인

    2. 런타임에러(100%): ArrayIndexOutOfBounds
    - n=1 일때 발생
    - dp init 코드가 아닌, getSolve2MinScore 에 n-2가 들어가기 때문

    3. 맞았습니다
    - 답을 안보고도 접근정도는 되어야할텐데 아직 멀었다
    
*/
private lateinit var dp: Array<Int>
private lateinit var bDp: Array<Array<Int>> // 바킹독님 방법
private lateinit var arr: Array<Int>
fun main(args: Array<String>){
    solve2()
}

// solve2 : 바킹독님이 제시하신 새로운 접근 방법
private fun solve2(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()    
    val n = br.readLine().toInt()
    var max = 0
    arr = Array(n+1){0}
    repeat(n){ arr[it+1] = br.readLine().toInt(); max+= arr[it+1] }

    dp = Array(n+1){-1}
    dp[0] = 0
    dp[1] = arr[1]
    if(n>2)dp[2] = arr[2]
    if(n>3)dp[3] = arr[3]

    if(n>=2){
        var res = Math.min(
            getSolve2MinScore(n-2),
            getSolve2MinScore(n-1)
        )
        bw.write("max[$max], res[$res]\n")
        res = max-res
        bw.write("$res\n")
    }else{
        bw.write("$max\n")
    }


    bw.flush()
    br.close()
    bw.close()
}
//top to bottom
private fun getSolve2MinScore(x: Int): Int{
    if(dp[x] != -1) return dp[x]
    dp[x] = Math.min(
        getSolve2MinScore(x-3),
        getSolve2MinScore(x-2)
    ) + arr[x]
    return dp[x]
}

// solve 바킹독님 접근 방식 => 2차원 dp
private fun solveBarkingDog(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    arr = Array(n+1){0}
    repeat(n){ arr[it+1] = br.readLine().toInt() }
    initBarkingDogDp(n+1)
    
    getBarkingDogMaxScore2(n)
    bw.write("${Math.max(bDp[n][1], bDp[n][2])}\n")
    
    bw.flush()
    br.close()
    bw.close()
}

private fun initBarkingDogDp(size: Int){
    bDp = Array(size){Array(3){-1}}
    for(i in 0..2) bDp[0][i] = 0
    bDp[1][1] = arr[1]
    bDp[1][2] = 0 // 해당 경우는 없지만 -1로 두면 base condition에 걸리니 0으로 설정
    if(size<=2) return
    bDp[2][1] = Math.max(bDp[0][1], bDp[0][2]) + arr[2]
    bDp[2][2] = bDp[1][1]+arr[2]
}
// top-bottom
private fun getBarkingDogMaxScore(x: Int, c: Int): Int {
    if(bDp[x][c] != -1){
        return bDp[x][c]
    }
    bDp[x][1] = Math.max(
        getBarkingDogMaxScore(x-2, 1),
        getBarkingDogMaxScore(x-2, 2)
    ) + arr[x]
    bDp[x][2] = getBarkingDogMaxScore(x-1, 1) + arr[x]
    return bDp[x][c]
}
// bottom-top
private fun getBarkingDogMaxScore2(x: Int) {
    for(i in 3..x){
        bDp[i][1] = Math.max(
            bDp[i-2][1],
            bDp[i-2][2]
        ) + arr[i]
        bDp[i][2] = bDp[i-1][1] + arr[i]
    }
}

/// solve 1 => 1차원 dp
private fun solve1(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val n = br.readLine().toInt()
    arr = Array(n+1){0}
    repeat(n){ arr[it+1] = br.readLine().toInt() }
    initDp(n+1)
    bw.write("${getMaxScore2(n)}\n")
    
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
// top-bottom
private fun getMaxScore(x: Int): Int{
    if(dp[x] != -1){
        return dp[x]
    }
    dp[x] = Math.max(
        getMaxScore(x-2),
        getMaxScore(x-3) + arr[x-1]
    ) + arr[x]
    return dp[x]
}
// bottom-top
private fun getMaxScore2(x: Int): Int{
    if(dp[x] != -1){
        return dp[x]
    }
    for(i in 3..x){
        dp[i] = Math.max(
            dp[i-2],
            dp[i-3] + arr[i-1]
        )+arr[i]
    }
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