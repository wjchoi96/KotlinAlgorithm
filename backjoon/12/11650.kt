//sliver 5
//12-6

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st : StringTokenizer

    val size = br.readLine().toInt()
    val arr = Array<Point>(size){Point(0,0)}
    for(i in 0 until size){
        st = StringTokenizer(br.readLine())
        arr[i] = Point(st.nextToken().toInt(), st.nextToken().toInt())
    }

    quickSort(arr, 0, size - 1)

    for(i in arr){
        bw.write("${i.x} ${i.y}\n")
    }
    
    bw.flush()
    bw.close()
    br.close()
}

private fun quickSort(a : Array<Point>, start : Int, end : Int){
    var pl = start
    var pr = end
    var x = a[(start + end)/2]

    print("a[$start]~a[$end] : {")
    for(i in a){
        print("$i ")
    }
    print("}\n")

    do{
        // pl 포인터의 값이 피벗보다 작다면
        while(a[pl].compare(x) < 0)pl++ 
        // pr 포인터의 값이 피벗보다 크다면
        while(a[pr].compare(x) > 0)pr-- 
        if(pl<=pr){
            swap(a, pl++, pr--)
        }
    }while(pl<=pr);

    if(start<pr) quickSort(a, start, pr)
    if(pl<end) quickSort(a, pl, end)
}

// 시간초과 -> 시간복잡도가 더 낮은 sort 를 찾아야한다
private fun bubbleSort(arr : Array<Point>, size : Int){
    for(i in 0 until size){
        for(j in size-1 downTo i+1){
            if(arr[j-1].compare(arr[j]) == 1){
                swap(arr, j-1, j)
            }
        }
    }
}

private fun swap(arr : Array<Point>, idx1 : Int, idx2 : Int){
    val t = arr[idx1]
    arr[idx1] = arr[idx2]
    arr[idx2] = t
}

class Point internal constructor(
    val x : Int,
    val y : Int
){
    fun compare(other : Point) : Int {
        return if(this.x > other.x){
            1
        }else if(this.x == other.x){
            if(this.y > other.y){
                1
            }else if(this.y==other.y){
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
    좌표 n개가 주어진다
    1. x좌표 순
    2. x좌표가 같다면 y좌표 순
*/