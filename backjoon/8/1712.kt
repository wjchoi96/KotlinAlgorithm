// bronze 4
// 8-1

import java.io.*
import java.util.StringTokenizer

/*
매년 임대료, 재산세, 보험료, 급여 등 A 고정치줄
노트북 생산비 재료비, 인건비 등 B 가변지출
노트북 가격 C

손익 분기점을 구하라

A + (B * 판매량) => 지출
(c * 판매량) => 수입

 */
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val st = StringTokenizer(br.readLine())
    try {
        val a = st.nextToken().toInt()
        bw.write("a : $a\n")
        val b = st.nextToken().toInt()
        bw.write("b : $b\n")
        val c = st.nextToken().toInt()
        bw.write("c : $c\n")
        bw.write("${checkBreakEvenPoint2(a, b, c)}\n")
    }catch(e : Exception){
        e.printStackTrace()
    }

    bw.flush()
    bw.close()
    br.close()
    
}

private fun checkBreakEvenPoint(fixedCost : Int, productionCost: Int, price : Int) : Int{
    for(i in 1 until Int.MAX_VALUE){
        val spend = fixedCost + (productionCost * i)
        val income = (price * i)
        if(spend > income)
            return -1
        else if(spend - income < 0)
            return i
    }
    return -1
}

private fun checkBreakEvenPoint2(fixedCost : Int, productionCost: Int, price : Int) : Int{
    /*
    
    1050
    2100, 8, 10
    2100 + (8 * n) - (10 * n) = 0
    2100 + n(8-10)
    n(8-10) = -2100
    (n * -2) = -2100
    (n) = -1050

    0 = 2100 + (8 * n) - (10 * n) 
    -2100 = n(8-10)
    -2100 = -2n 
    2100 = 2n

    i = fixedCost + (i*(productionCost - price))
    -fixedCost = (i*(productionCost - price))
    -fixedCost / (productionCost - price) = i
     */
    if(productionCost >= price) // 생산비가 더 비싼경우
        return -1
    return  -fixedCost / (productionCost - price) + 1
}