//silver5
// 11-3

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val size = br.readLine().toInt()
    val arr = Array<Point>(size){Point(0,0)}

    var st : StringTokenizer

    for(i in 0 until size){
        st = StringTokenizer(br.readLine())
        arr[i].setPoint(st.nextToken().toInt(), st.nextToken().toInt())
    }

    for(i in 0 until size){
        var count = 0
        for(j in 0 until size){
            if(arr[i].comparePoint(arr[j]) < 0){
                count++
            }
        }
        bw.write("${count+1} ")
    }

    bw.flush()
    bw.close()
    br.close()
}

class Point internal constructor(
    var x : Int,
    var y : Int
){
    fun setPoint(x : Int, y : Int){
        this.x = x
        this.y = y
    }
    fun comparePoint(other : Point) : Int {
        val xCompare = this.x - other.x
        val yCompare = this.y - other.y
        if(xCompare > 0 && yCompare > 0){
            return 1
        }else if(xCompare < 0 && yCompare < 0){
            return -1
        }else{
            return 0
        }
    }
}

/*
    xkg, ycm -> (x,y)
    x,y 가 둘다 더 큰사람이 덩치가 더 크다
    덩치가 큰 순으로 등수가 매겨짐 => 자기보다 덩치가 큰사람이 k명이라면 나는 k+1 등
    => 동률 가능

    
 */