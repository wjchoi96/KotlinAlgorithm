//sliver 5
//12-6

import java.io.*
import java.util.StringTokenizer
import kotlin.io.readLine
fun main(args : Array<String>){
    Solution11651().solve()
}

class Solution11651{
    fun solve(){
        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.out))
        var st : StringTokenizer

        val size = br.readLine().toInt()
        val a = Array<Point>(size){Point(0,0)}
        for(i in 0 until size){
            st = StringTokenizer(br.readLine())
            a[i] = Point(st.nextToken().toInt(), st.nextToken().toInt())
        }
        quickSort2(a, 0, size-1)

        for(i in a){
            bw.write("${i.x} ${i.y}\n")
        }

        bw.flush()
        bw.close()
        br.close()
    }
    private fun quickSort2(a : Array<Point>, start : Int, end : Int){
        var pl = start
        var pr = end
        val x = a[(start + end)/2]

        print("$a[$start] ~ a[$end] : {")
        for(i in a){
            print("$i ")
        }
        print("}\n")

        do{
            // pl포인터의 값이 피벗보다 커질때까지 반복
            while(a[pl].compareY(x) < 0) pl++
            // pr포인터의 값이 피벗보다 작아질때까지 반복
            while(a[pr].compareY(x) > 0) pr--
            if(pl<=pr){
                swap(a, pl++, pr--)
            }
        }while(pl<=pr);

        if(start < pr){
            quickSort2(a, start, pr)
        }
        if(pl < end){
            quickSort2(a, pl, end)
        }
    }
    private fun swap(arr : Array<Point>, idx1 : Int, idx2 : Int){
        val t = arr[idx1]
        arr[idx1] = arr[idx2]
        arr[idx2] = t
    }

    private class Point internal constructor(
        val x : Int,
        val y : Int
    ){
        fun compareY(other : Point) : Int {
            return if(this.y > other.y){
                1
            }else if(this.y == other.y){
                if(this.x > other.x){
                    1
                }else if(this.x==other.x){
                    0
                }else{
                    -1
                }
            }else{
                -1
            }
        }

        override fun toString() : String {
            return "($x $y)"
        }
    }

/*
    11650 과 같지만
    y 좌표 우선
*/
}
