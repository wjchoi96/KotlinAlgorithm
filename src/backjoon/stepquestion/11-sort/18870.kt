//sliver2
//12-10

import java.io.*
import java.util.StringTokenizer
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val size = br.readLine().toInt()
    val a = Array<Int>(size){0}
    val st = StringTokenizer(br.readLine())
    for(i in 0 until size){
        a[i] = st.nextToken().toInt()
    }
    val sroted = a.sorted()
    
    val map : HashMap<Int, Int> = HashMap()
    var rank = 0 
    for(i in 0 until size){
        if(!map.containsKey(sroted[i])){
            map.put(sroted[i], rank++)
        }
    }

    for(i in 0 until size){
        bw.write("${map.get(a[i])} ")
    }
    bw.write("\n")


    bw.flush()
    bw.close()
    br.close()
}

/*
    ??만약 정확한 값이 필요 없고 값의 대소 관계만 필요하다면, 
    모든 수를 0 이상 N 미만의 수로 바꿀 수 있습니다.??


    좌표 압축
    xi(압) 은 xi>xj 를 만족하는 서로 다른 좌표의 개수와 같아야한다
    => xi 보다 작은 좌표의 개수

    5
    2 4 -10 4 -9
    =>
    2 3 0 3 1

    2의 압축 => 2
    2>-10, 2>-9

    4의 압축 => 3 
    4>-10, 4>-2, 4>2

    도수 정렬을 통해서 자기보다 작은 값들을 모두 더하면 될거같은데
    -> 도수 아닌거같다, 범위가 너무 넓음

    중복을 제외하고 오름차순으로 정렬한다면
    idx 번이 곧 압축된 결과물이다
*/