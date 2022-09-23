/*
    카카오 2022 블라인드 001
    level 1
    https://school.programmers.co.kr/learn/courses/30/lessons/92334

    불량 이용자를 신고하고 처리 결과를 메일로 전달

    - 각 유저는 한번에 한명의 유저를 신고 가능
    - 한유저를 여러번 신고할 수 있지만, 1회로 간주됨
    - 신고 횟수에 제한은 없음

    - k번 이상 신고된 유저는 게시판 이용 정지
    해당 유저를 신고한 유저들에게 모두 메일 발송
    - 유저가 신고한 모든 내용을 취합하여 마지막에 한꺼번에 게시판 이용 정지를 시키며 메일 발송

    각 유저별로 처리결과 메일을 받은 횟수를 리턴
*/
/*
    map<String, Map<String, Int>>
    map[user][reportUser] = reportCount

    reportCountMap = map<String, Int> // 유저가 받은 유효한 신고 횟수 저장
    reportUserMap = map<String, Set<String>> // 해당 유저를 유효하게 신고한 유저 집합. 검색을 O(1)에 수행하기 위해 set으로 저장. 
    // 그치만 어차피 해당 유저들에게 모두 메일을 보내야 하는데, List로 해도 될듯
    // 하지만 중복방지까지 생각하면 Set이 나을듯

    report를 순회하며 reportCountMap, reportUserMap에 유효한 데이터 저장
    reportCountMap user들을 대상으로 ban 유저 탐색
    ban유저에게 메일을 보낼 유저들을 reportUserMap에서 탐색
*/

fun main(args: Array<String>){
    val ids = arrayOf(
        "muzi", "frodo", "apeach", "neo"
    )
    val reports = arrayOf(
        "muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"
    )
    KakaoBlind2022001().solution(ids, reports, 2)
}

class KakaoBlind2022001 {
    private val reportCountMap: MutableMap<String, Int> = mutableMapOf() // 유저가 받은 유효한 신고 횟수 저장
    private val reportUserHistoryMap: MutableMap<String, MutableSet<String>> = mutableMapOf() // user를 검색한 유저 목록
    private val receiveMailMap: MutableMap<String, Int> = mutableMapOf() // 유저가 받은 메일 수

    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {

        repeat(report.size) { idx ->
            report[idx].split(' ').let { 
                reportUser(it[0], it[1])    
            }
         }
        banUser(k)
        println(receiveMailMap)

        val res = IntArray(id_list.size){0}
        id_list.forEachIndexed { i, id ->
            res[i] = receiveMailMap.getOrDefault(id, 0)
        }
        println(res.toList())

        return res
    }

    private fun reportUser(from: String, to: String){
        val history = reportUserHistoryMap.getOrDefault(to, mutableSetOf())
        if(history.contains(from)){  // 중복 신고
            return
        }
        reportUserHistoryMap[to] = history.apply{ add(from) }
        reportCountMap[to] = reportCountMap.getOrDefault(to, 0) + 1
    }

    private fun banUser(reportCountForBan: Int){
        reportCountMap.keys.forEach { id ->
            if(reportCountMap.getOrDefault(id, 0) >= reportCountForBan){
                sendMail(id)
            }
        }
    }

    private fun sendMail(banUser: String){
        reportUserHistoryMap[banUser]?.forEach { 
            receiveMailMap[it] = receiveMailMap.getOrDefault(it, 0) + 1
        }
    }
}