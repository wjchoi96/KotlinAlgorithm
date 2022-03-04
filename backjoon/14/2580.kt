//gold4
//14-6
/*
    참고 : https://st-lab.tistory.com/119
    참고 : https://infodon.tistory.com/64
*/
import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader((System.`in`)))

    val size = 9
    val arr : Array<Array<Int>> = Array(size){ Array(size){0} }
    var st : StringTokenizer
    for(y in 0 until 9){
        st = StringTokenizer(br.readLine())
        for(x in 0 until 9){
            arr[y][x] = st.nextToken().toInt()
        }
    }

    br.close()
    dfs(0, 0, arr, bw)

    bw.flush()
    bw.close()
}

private fun dfs(y : Int, x : Int, arr : Array<Array<Int>>, bw : BufferedWriter){
    // 가로행부터 검사하도록
    if(x == 9){ // 가로열을 다 훑었다면
        dfs(y+1, 0, arr, bw) // 세로열을 하나 증가시켜서 훑는다
        return
    }
    if(y==9){ // y가 마지막 행까지 완료하고 다음 재귀를 왔을때 종료
        bw.write("\n")
        for(yy in 0 until 9){
            for(xx in 0 until 9){
                bw.write("${arr[yy][xx]} ")
            }
            bw.write("\n")
        }
        bw.flush()
        bw.close()
        System.exit(0) // 여기서 프로그램 자체를 종료해야하는것 놓침
        // return 했었으나, 다음 재귀가 배열을 건드려버려서 틀린탑이 되어버린다
    }
   
    if(arr[y][x] == 0){
        for(i in 0 until 9){
            if(isSafeSmallRect(arr, x, y, i+1) && isSafeRowCol(arr, x, y, i+1)){
                // 해당 노드에 값 대입
                arr[y][x] = i+1
                // printSdocku(9, arr)
                dfs(y, x+1, arr, bw) // 오른쪽으로 한칸 이동
                // 재귀가 끝나면 해당 노드 다시 0으로 초기화
                arr[y][x] = 0 // 이것도 놓쳤었다
            }
        }   
    }else{
        dfs(y, x+1, arr, bw)
    }
    
}
private fun isSafeSmallRect(arr : Array<Array<Int>>, x : Int, y : Int, value : Int) : Boolean {
    val smallX = x/3 * 3
    val smallY = y/3 * 3
    for(rectY in smallY until smallY+3){
        for(rectX in smallX until smallX+3){
            if( value == arr[rectY][rectX] )
                return false
        }
    }
    return true
}
private fun isSafeRowCol(arr : Array<Array<Int>>, x : Int, y : Int, value : Int) : Boolean {
    for(i in 0 until 9){
        // x좌표고정 y좌표 체크(열 체크)
        if(arr[i][x] == value){ // 해당 열에 같은 수가 있다면 false
            return false
        }
         // y좌표고정 x좌표 체크(행 체크)
        else if(arr[y][i] == value){ // 해당 행에 같은 수가 있다면 false
            return false
        }
    }
    return true
}


// for debug
private fun printSdocku(size : Int, arr : Array<Array<Int>>){
    print("debug\n")
    for(y in 0 until size){
        for(x in 0 until size){
            print("${arr[y][x]} ")
        }
        print("\n")
    }
    print("\n")
}
/*
    9 * 9 
    3*3 사각형이 3*3 형태로 존재

    1. 9*9 전체를 순회하긴 해야하겠지? 

    1-1. 현재 node 가 속한 작은 사각형 범위를 알아내기
    1-2. 현재 가로, 세로열 알아내기

    1. (0,0)부터 (8,8)까지 훑으며 빈칸에서 작업 수행
    2. 빈 노드 도착시, 1-1, 1-2 수행
    3-1. 값을 하나씩 넣어보면서 작은사각형, 가로, 세로에서 안전한지 확인?
    3-2. 작은사각형 순회해서 입력 가능한 수를 먼저 추린다? -> 이게 나을듯?
    4. 작은 사각형에서 입력 가능한 수를 하나씩 넣어보며 가로 세로 안전 확인
    5. 값 입력에 성공했다면 다음 노드 방문


    1-1 좌표 하나를 줬을때 해당 좌표가 속한 작은 사각형 범위를 알아내는 함수?
    (0,0) ~ (2,2) (0,3) ~ (2,5) (0,6) ~ (2,8)

    (3,0) ~ (5,2) (3,3) ~ (5,5) (3,6) ~ (5,8)

    (6,0) ~ (8,2) (6,3) ~ (8,5) (6,6) ~ (8,8)

    (0,0) (0,3) (0,6)   (2,2) (2,5) (2,8)
    (3,0) (3,3) (3,6) ~ (5,2) (5,5) (5,8)
    (6,0) (6,3) (6,6)   (8,2) (8,5) (8,8)

    (n,n)
    시작좌표 x : (n/3) * 3
    시작좌표 y : (n/3) * 3

    0,1,2 -> 2, 3,4,5 -> 5, 6,7,8 -> 8
    끝좌표 x : 시작좌표 + 2 
    끝좌표 y : 시작좌표 + 2

    (4,7) -> (3,6) ~ (5,8) 
*/