// gold2
/*
    바킹독님 알고리즘 강의 중 시뮬레이션 유형

    2048

    m*n board
    전체 블록을 상하좌우 네 방향중 하나로 이동시킬 수 있다
    같은 값을 가지는 두 블록이 충돌하면 두 블록은 하나로 합쳐지게 된다

    각 블록들은 한번의 이동에 한번의 합체밖에 못한다
    블록은 추가되지 않는다

    똑같은 수가 세개가 있는경우는, 이동하려고 하는 쪽의 칸이 먼저 합쳐진다
    예) 왼쪽으로 이동하는경우 y idx가 적은 블록들이 우선권을 가진다
    -> 이동하려고 하는쪽들의 블록을 먼저 합치게 하면 될듯

    최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값을 구하라

    1 <= N <= 20
    0 : 빈칸
    이외의값 : 블록의 값
    2 <= 블록값 <= 1024, 2의 제곱꼴

    블록은 적어도 하나 주어진다
*/
/*
    블록을 미는 기능
    블록을 합치는 기능
    하나의 이동에 각 블록은 한번만 합체할 수 있는 기능
*/

private var n = 0
fun main(args : Array<String>){
    val bw = System.out.bufferedWriter()
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()

    bw.write("check")

    bw.flush()
    bw.close()
    br.close()
}