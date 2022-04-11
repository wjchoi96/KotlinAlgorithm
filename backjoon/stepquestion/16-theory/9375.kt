//sliver3
//16-10
/*
    test case <= 100
    의상의 수 0 <= n <= 30
    의상의 이름, 종류가 공백으로 구분

    같은 종류의 의상은 하나만 입을 수 있다

    A 종류2개
    B 종류1개

    조합 가능한 수 
    A1
    A2
    B
    A1,b
    A2,b
    -> 5가지

    A 종류2개 + 안입는 선택지 => 3
    B 종류1개 + 안입는 선택지 => 2

    3C1 * 2C1 = 3*2 = 6
    둘다 안입는 선택지 -1
    => 5


*/
import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))    
    var st : StringTokenizer
    val size = br.readLine().toInt()

    for(i in 0 until size){
        val cMap = HashMap<String, Int>()
        val cCount = br.readLine().toInt()
        for(j in 0 until cCount){
            st = StringTokenizer(br.readLine())
            st.nextToken()
            val item = st.nextToken()
            if(cMap.containsKey(item)){
                cMap.put(item, cMap[item]!!+1)
            }else{
                cMap.put(item, 1)
            }
        }

        var result = 1
        for(c in cMap.values){
            result *= c+1
        }
        bw.write("${result-1}\n")
    }
   
    
    bw.flush()
    bw.close()
    br.close()
}