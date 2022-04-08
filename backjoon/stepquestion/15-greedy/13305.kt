// sliver 4
// 16-5

/*
    왼쪽끝에서 오른쪽끝 도시로 이동
    길이의 단위는 km

    기름0에서 시작, 기름통 크기 무제한

    1km 마다 1리터 기름 사용
    도시당 하나의 쥬유소, 도시마다 가격이 다를 수 있다

    최소의 비용 계산

    도시의 개수 n
    2 <= n <= 100000
    도로의 길이 n-1 개

    거리
    1<= 거리 <= 1000000000
    리터당 가격
    1<= 가격 <= 1000000000

    서브태스트 
    => 한 번의 제출로 모든 서브태스크를 통과할 수 있으면 만점을 받습니다.
    1. 모든 주유소의 리터당 가격은 1원이다.
    2. 2 ≤ N ≤ 1,000, 제일 왼쪽 도시부터 제일 오른쪽 도시까지의 거리는 최대 10,000, 리터 당 가격은 최대 10,000이다.
    3. 원래의 제약조건 이외에 아무 제약조건이 없다.


    =========================
    1. 제일 기름 가격이 싼 도시를 탐색
    2. 해당 도시까지 최소한의 기름만 넣고 간다
    3. 제일 싼 도시에 도착해서 부족한 기름을 전부 넣는다

    2_1. 해당 도시까지 기름가격이 제일 싼 도시를 찾는다
    2_2. 반복

    ==========================
    도로의 길이 배열 
    roadArr[idx] = value
    idx : 도로의 idx
    value : 길이

    도시 배열
    city[idx] = value
    idx : 도시의 번호
    value : 기름 가격

    ===========================
    1. 현재 도시의 기름 가격이 다음 도시보다 비싸거나 같다면
    -> 다음 도시까지의 갈 수 있는 기름을 넣는다
    2. 현재 도시의 기름가격이 다음 도시보다 싸다면
    -> 그 다음도시보다 싼지 체크
    -> 반복해서 같거나 더 비싼 도시를 만날때까지(혹은 끝까지)의 거리 측정
    -> 해당 거리만큼 기름 넣는다


    ===========================
    4: 2
    3: 5
    1: 7
    2: 9
    => 4번도시가 제일싸네
    => 3번도시가 2번째로 싸네
    => 1번도시 에서 3번도시까지 채우면 되겠군
    => 

    1000000000

    ===========================
    최적화!!!
    내림차순!!
    리터당 기름 값이 '내림차순'일 경우에 주유하면 된다.
    8, 9, 5, 4, 2, 7, 1  → 8, 8, 5, 4, 2, 2, 1
    각 도시를 순회하면서 현재 도시의 주유값이 지나온 도시보다 작다면 주유할 값 갱신
*/

import java.io.*
import java.util.StringTokenizer
private lateinit var cityArr : Array<Long>
private lateinit var roadArr : Array<Long>
private var price : Long = 0
fun main(args : Array<String>){ 
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st : StringTokenizer

    val size = br.readLine().toInt()

    roadArr = Array<Long>(size-1){-1}
    st = StringTokenizer(br.readLine())
    for(i in 0 until size-1){
        roadArr[i] = st.nextToken().toLong()
    }

    st = StringTokenizer(br.readLine())
    cityArr = Array<Long>(size) {-1}
    for(i in 0 until size){
        cityArr[i] = st.nextToken().toLong()
    }

    var minOilPrice = cityArr[0]
    for(i in 0 until size-1){
        minOilPrice = Math.min(minOilPrice, cityArr[i])
        price += minOilPrice * roadArr[i]
    }


    bw.write("$price\n")
   
    br.close()
    bw.flush()
    bw.close()
}

private fun chargeOil(start : Int, end : Int, oilIdx : Int){
    price += cityArr[oilIdx]*roadArr[start]
    print("${start}번째 도시에서 ${end}번째 도시로 가기위해 ${oilIdx}도시에서 ${cityArr[oilIdx]*roadArr[start]}주유\n")
}

private fun goTrip(cityCount : Int){
    var i = 0
    while(i < cityCount-1){
        //1. 현재도시의 유가가 다음도시보다 비싸거나 같다면 다음 도시까지의 기름만 챙긴다
        if(cityArr[i] >= cityArr[i+1]){
            chargeOil(i, i+1, i) // 기름 넣고
            i++ // 출발
        }
        //1. 현재도시의 유가가 다음도시보다 싸다면
        //2. 현재도시보다 유가가 더 싼 도시를 발견할때까지 갈수 있게 기름을 넣는다
        else{
            // 현재 point 도시에 정박
            val point : Int = i
            // 다음 도시들의 유가를 확인한다 -> 현재 도시보다 유가가 싼 도시를 찾는다
            for(j in point+1 until cityCount){
                chargeOil(i, j, point)  // 현재 확인중인 도시까지는 가야하니 그만큼 주유한다
                print("${point}도시에서 ${j}도시 유가 확인중\n")
                // 현재 도시보다 더 싼 도시를 찾거나, 마지막 도시를 발견했다
                if(cityArr[point] > cityArr[j] || j == cityCount-1){ 
                    i = j // j 도시까지 출발
                    break
                }
                i = j
            }
        }
    }
}