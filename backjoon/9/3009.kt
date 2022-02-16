// bronze 3
// 9-8

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st = StringTokenizer(br.readLine())
    val a = RectBuilder.Point(st.nextToken().toInt(), st.nextToken().toInt())
    st = StringTokenizer(br.readLine())
    val b = RectBuilder.Point(st.nextToken().toInt(), st.nextToken().toInt())
    st = StringTokenizer(br.readLine())
    val c = RectBuilder.Point(st.nextToken().toInt(), st.nextToken().toInt())


    val point = RectBuilder(a, b, c).getDPoint()
    bw.write("${point.x} ${point.y}\n")

    bw.flush()
    bw.close()
    br.close()

}

class RectBuilder internal constructor(
    val a : Point,
    val b : Point,
    val c : Point
) {
    class Point internal constructor(
        val x : Int,
        val y : Int
    ){}
    fun getDPoint() : Point {
        val x = if(a.x == b.x) 
            c.x
        else if(a.x == c.x)
            b.x
        else
            a.x

        val y = if(a.y == b.y) 
            c.y
        else if(a.y == c.y)
            b.y
        else
            a.y
        return Point(x,y)
    }
}

/*
    평행한 직사각형을 그리려면
    x, y 의 차이가 같아야 한다

    점 3개가 주어졌을때
    30 20
    11 10
    10 11
    20 30
*/