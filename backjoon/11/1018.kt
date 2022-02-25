//sliver 5
// 11-4

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st : StringTokenizer = StringTokenizer(br.readLine())
    val height = st.nextToken().toInt()
    val width = st.nextToken().toInt()

    val arr = Array<String>(height){""}
    for(i in 0 until height){
        arr[i] = br.readLine()
    }

    var min = 1000
    
    for(x in 0 until height-7){
        for(y in 0 until width-7){
            var count1 = 0
            var count2 = 0
            var firstColor : Char = arr[x][y]
            print("($x, $y) ~ (${x+8-1}, ${y+8-1}).firstColor($firstColor) ")
            for(h in x until x+8){
                for(w in y until y+8){
                    // print("($h, $w)\n")
                    if((h+w)%2 == 0){
                        if(arr[h][w] != firstColor){
                            count1++
                        }else{
                            count2++
                        }
                    }else{
                        if(arr[h][w] == firstColor){
                            count1++
                        }else{
                            count2++
                        }
                    }
                }
            }
            print("count1 : $count1, count2 : $count2\n")
            if(count1 <= count2 && count1 < min){
                min = count1
            }else if(count2 <= count1 && count2< min){
                min = count2
            }
            for(h in x until x+8){
                for(w in y until y+8){
                   print("${arr[h][w]}")
                }
                print("\n")
            }

        }
    }
    bw.write("$min\n")

    bw.flush()
    bw.close()
    br.close()
}

/*
    체스판 오류 찾아서 고쳐야 할 타일의 개수 찾기
    2초
    8<= N <= 50

    (0,0)의 아이템은 x + y 가 짝수인 경우 나타나야 하는 item

    WBWBWBWB
    BWBWBWBW
    WBWBWBWB
    BWBWBWBW
    WBWBWBWB
    BWBWBWBW
    WBWBWBWB
    BWBWBWBW

    (0,0)이 W 인 경우
    (0,0), (0,2), (0,4)
    (1,1), (1,3) ...
    x + y 가 짝수인경우 w, 홀수면 b

    반대는 반대

    입력된 칸에서 8*8 로 자르는거였네?
    x를 
    0~8, 1~9, 2~10, ...  w-8~w

 */