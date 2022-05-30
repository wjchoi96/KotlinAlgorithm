
/*
    프로그래머스 고득점 kit
    해시유형
    level 3

    베스트 앨범

    스트리밍 사이트에서 장르별로 가장 많이 재생된 노래를 두개씩 모아 베스트앨범 출시

    노래는 고유번호로 구분

    1. 속한 노래가 많이 재생된 장르를 먼저 수록
    2. 장르내에서 많이 재생된 노래를 먼저 수록
    3. 장르 내에서 재생횟수가 같은 노래는 고유번호가 낮은 노래를 먼저 수록
    - 장르에 속한 곡이 하나라면 하나의 곡만 선택

    genres[i] : 고유번호가 i인 노래의 장르
    play[i] : 고유번호가 i인 노래가 재생된 횟수
    노래 : i

    generes, plays의 길이는 같으며 1<= n <=10,000
    장르의 종류는 100개 미만
    모든 장르는 재생된 횟수가 다르다

    베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return
*/
import kotlin.collections.toList
import kotlin.time.seconds

fun main(args : Array<String>){
    val generes = arrayOf(
        "classic", "pop", "classic", "classic", "pop", "classic"
    )
    val plays = intArrayOf(
        500, 600, 150, 800, 2500, 800
    )
    val res = shortCode(generes, plays) //solution(generes, plays)
    for(i in res){
        print("$i ")
    }
    print("\n")

}

fun solution(generes: Array<String>, plays: IntArray): IntArray {
    // var answer = intArrayOf()
    val n = generes.size

    // genersMap 에는 장르별 재생횟수가 저장된다
    val gMap = HashMap<String, Int>()
    val gMusicMap = HashMap<String, ArrayList<Int>>()
    for(i in 0 until n){
        // 해당 노래의 재생수를 해당 장르 재생수에 더해준다
        gMap[generes[i]] = gMap.getOrDefault(generes[i], 0) + plays[i]
        gMusicMap[generes[i]] = gMusicMap.getOrDefault(generes[i], ArrayList<Int>()).apply { 
            add(i)
         }
    }

    val gSortList = ArrayList(gMap.toList())
    gSortList.sortBy{-it.second}

    val res = ArrayList<Int>()
    for(i in 0 until gSortList.size){
        val category = gSortList[i].first
        // 장르별 음악 idx list
        val musicList = gMusicMap.getOrDefault(category, ArrayList())
        // 재생이 많은 순, 재생횟수가 같다면 번호가 작은순으로 정렬
        musicList.sortWith(compareBy(
            {-plays[it]}, {it}
        ))

        for(i in 0 until musicList.size){
            if(i == 2){ // 장르별 2개까지만 저장
                break
            }
            res.add(musicList[i])
        }
    }
    return res.toIntArray()
}

private fun shortCode(genres: Array<String>, plays: IntArray) : IntArray {
    return genres.indices.groupBy { genres[it] } // list에서 두 값을 묶어서 Map<key, value> 로 만들어준다, key: group을 묶어둘 조건, value: key 조건에 만족하는 원소들 리스트
        .toList()
        .sortedByDescending { it.second.sumBy { plays[it] } }
        .map { it.second.sortedByDescending { plays[it] }.take(2) }
        .flatten()
        .toIntArray()
}