//sliver1
/*
    바킹독님 재귀단원 강의 중 제시된 연습문제


    2^n * 2^n 배열을 z모양으로 탐색
    (0,0) (0,1) (1,0) (1,1) 으로 탐색

    n>1인 경우 
    2^n-1 * 2^n-1 로 4등분 후 재귀적으로 순서대로 방문
    
    N, r, c
    r행 c열을 몇번째로 방문하는지 출력 (r, c)
    1 <= N <= 15
    0<= r,c <= 2^n

    n 이 1 이면
    (0,0) (0,1) (1,0) (1,1)

    n이 2이면
    (0,0) (0,1) (1,0) (1,1)
    (0,3)


    기준x,y 로부터 이동
    dest = arrayOf(Pair(0, 0), Pair(0, 1), Pair(1, 0), Pair(1, 1))

    다음 기준점을 잡는 방법
    n == 1
    0,0

    n == 2
    0,0
    0,2
    2,0
    2,2

    n == 3
    0,0
    0,2
    2,0
    2,2

    0,4
    0,6
    2,4
    2,6

    귀납적 사고를 해보자
    2^1 크기의 배열의 방문순서를 알아 낼 수 있다
    
    2
    2^n 크기의 배열의 방문순서를 알아 낼 수 있다
    
*/

/*
    n이 3일때 특정칸을 몇번째로 방문하는지 알아내기 (8*8)

    n이 2일떄 결과를 알고있는 상태라면 (4*4)

    r=2, c=2 의 방문순서를 바로 알 수 있다

    n^4 사각형의 첫번째 구역안에 속해있기때문

    r = 6, c = 2
    3번째 구역에 속해있다
    => 1번쨰 구역 + 2번째 구역 의 칸의 수 + 12
    => (2^n-1 * 2^n-1)*2 + 12 
    => 32 + 12 = 44

    n=k일때의 결과를 가지고, n=k+1의 결과를 구할때 사용 가능

*/

/*
    2^n * 2^n 배열에서 (x,y)를 방문하는 횟수

    1,2,3,4 사각형중 어느곳에 포함되어있는지 확인

    n=3( 8*8 ) 일때 (1,1) 

    getPoint(3, 1, 1)
    half = 2, 1번쨰 구역
    call getPoint(2, 1, 1)
    => return 3

    getPoint(2, 1, 1)
    half = 1, 4번째 구역
    call 3*half*half + getPoint(1,1,1)
    => 3 + 0
    => return 3

    getPoint(1,1,1)
    half = 0, 4번쨰 구역
    call 3*half*half + getPoint(0,0,0)
    => return 0
   
    getPoint(0,0,0)
    => return 0

*/
import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val x = st.nextToken().toInt()
    val y = st.nextToken().toInt()

    bw.write("${getPointOrder(n, x, y)}\n")

    bw.flush()
    bw.close()
    br.close()
}
/*
    getPointOrder(2, 3, 1)
    half : 2
    3area
    => 2 + getPointOrder(1, 1, 1) called
    => 2 + 0

    getPointOrder(1, 1, 1)
    half : 1
    4area
    => 0 + getPointOrder(0, 1, -1) called
    => 0
*/
private fun getPointOrder(n : Int, x : Int, y : Int) : Int{
    if(n == 0){
        return 0
    }
    val half = getTwoPower(n-1) // half는 한 변 길이의 절반, 즉 2^n-1
    val res = when{
        x<half && y<half -> getPointOrder(n-1, x, y)
        x<half && y>=half -> half*half + getPointOrder(n-1, x, y-half)
        x>=half && y<half -> 2*half*half + getPointOrder(n-1, x-half, y)
        else -> 3*half*half + getPointOrder(n-1, x-half, y-half)
    }
    return res
}

// 1<= n <= 15이니 int 형으로도 충분
private fun getTwoPower(n : Int) : Int{
    if(n == 1){
        return 2 
    }else if(n == 0){
        return 1
    }
    var res = getTwoPower(n/2)
    res *= res
    if(n%2 == 0){
        return res
    }
    return res*2
}
