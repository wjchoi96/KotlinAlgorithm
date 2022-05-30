// silver 2
// 9-11

import java.io.*
import java.util.StringTokenizer

fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val size = br.readLine().toInt()
    for(i in 0 until size){
        val st = StringTokenizer(br.readLine())
        bw.write("${getPoint(
            st.nextToken().toInt(),
            st.nextToken().toInt(),
            st.nextToken().toInt(),
            st.nextToken().toInt(),
            st.nextToken().toInt(),
            st.nextToken().toInt()
            )}\n")
    }

    bw.flush()
    bw.close()
    br.close()
}

private fun getPoint(x : Int, y : Int, n : Int, a : Int, b : Int, n2 : Int) : Int{
    val r : Int
    val r2 : Int
    if(n>n2){
        r = n
        r2 = n2
    }else{
        r = n2
        r2 = n
    }
    val x1 : Int
    val x2 : Int
    if(x>a){
        x1 = x
        x2 = a
    }else{
        x1 = a
        x2 = x
    }
    val y1 : Int
    val y2 : Int
    if(y>b){
        y1 = y
        y2 = b
    }else{
        y1 = b
        y2 = y
    }

    val d = (x1-x2) * (x1-x2) + (y1-y2) * (y1-y2)
    val add = (r+r2) * (r+r2) // 반지름 끼리의 합
    val sub = (r-r2) * (r-r2) // 반지름 끼리의 차이
    // 다 제곱하는 이유는 두 점 사이의 거리 구하기를 피타고라스의 정리를 사용했기때문
    // 제곱근한걸 풀어주기보단 싹다 제곱근해서 계산하는게 편하기 때문

    var res = 0
    when{
        d == 0 -> { // 두 점이 같은 위치에 있을때
            if(r == r2) // 두 반지름이 같다면
                res = -1 // 같은원 -> 무한대
            else  // 반지름이 다르다면 만날일없다 -> 하나가 나머지를 포함한 경우
                res = 0
        }
        add < d -> res = 0 // 두 원이 서로 떨어져있을때
        d < sub -> res = 0 // 하나의 원이 다른 하나를 포함하여 만나지 않을때
        sub < d && d < add -> res = 2
        add == d -> res = 1 // 외접
        sub == d -> res = 1 // 내접
        else -> {print("else")}
    }
    return res
}

/*

    원방정식
    https://st-lab.tistory.com/90 -> 이거 하나면댐
    https://travelerfootprint.tistory.com/61

    x1 에서 r1 만큼 떨어져있고
    x2 에서 r2 만큼 떨어져있는
    모든 좌표를 탐색 -> 개수 출력 -> 무한대라면 -1 출력

    중점이 (x1, y1) 이고 반지름이 n1 인 원
    중점이 (x2, y2) 이고 반지름이 n2 인 원
    
    1. 중점과 반지름이 모두 같다면 -> 무한대
    2. 접점이 없을때 :
     - 두원이 멀리 떨어진 경우 :  두 점 사이의 거리의 합이 두 반지름의 합보다 클때
     - 하나의 원이 다른 원을 포함하여 만나지 않는경우 : 두 점 사이의 거리의 합이 두 반지름의 차보다 작을때
    3. 접점이 하나일때 :
     - 외접 : 두 점 사이의 거리의 합 == 반지름의 합
     - 내접 : 
    4. 접점이 두개일때
     - 두 점 사이의 합이 반지름의 합보다 작을때
     - 두 점 사이의 합이 반지름의 차보다 클때 -> 
    

*/