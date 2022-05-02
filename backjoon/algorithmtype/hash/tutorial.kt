/*
    바킹독님 알고리즘 강의 중 해시 단원
    https://blog.encrypted.gg/1009

    해시란 무엇인가
    키에 대응되는 값을 저장하는 자료구조
    insert, erase, find, update 등 모든 연산이O(1) 

    각 key값을 배열의 인덱스로 사용하는 테이블에 값을 저장
    하지만 모든 key의 값에 대응하기엔 용량이 부족하니 각 key의 일정 부분만을 key로 활용하여 사용한다
    이때 사용되는것이 해시 함수
    : 임의 길이의 데이터를 고정된 길이의 데이터로 대응시키는 함수

    
    충돌 회피에 관련해선 다시 공부해보자
    한번 쭉 읽고 이해하긴했는데, 구현해보려면 더 공부해야할듯

    충돌회피
    1. Chaining
    2. Open Address

    =========kotlin hash collection 정리
    hash map : https://kkh0977.tistory.com/648
    hash map 정렬  // https://velog.io/@changhee09/Kotlin-Map-%EC%A0%95%EB%A0%AC
    1. list로 변환 후 정렬 #7786.kt
    2. comparator을 인자로 넘겨 toStortedMap 사용 #7786.kt

    연습문제
    1. 백준 7786(solve)
     - 해시를 안쓰고 이분탐색으로도 풀어보자
*/