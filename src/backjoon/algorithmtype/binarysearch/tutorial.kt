/*
    바킹독님 알고리즘 강의 중 이분탐색 단원
    https://blog.encrypted.gg/985

    # 이분탐색
    정렬되어 있는 배열에서 특정 데이터를 찾기 위해 모든 데이터를 순차적으로 확인하는 대신,
    탐색범위를 절반으로 줄여가며 찾는 탐색 방법

    # 시간복잡도
    선형탐색은 O(n)
    이분탐색은 O(lg N)

    # 구현
    start = 0
    end = size-1
    mid = (start+end)/2 
    로 시작
    찾는 값이 mid보다 작다면 end = mid-1
    찾는 값이 mid보다 크다면 start = mid+1
    로 진행해 나간다
    start와 end값이 교차하게 될때까지 값을 찾지 못했다면 값이 없는것

    # Parametric Search
    : 조건을 만족하는 최소/최댓값을 구하는 문제(최적화 문제)를 결정 문제로 변환해 이분 탐색을 수행하는 방법
    : 매개 변수 탐색
    !! 그리디 단원 잘못된 그리디 예제 중 하나가 parametric search로 풀린다(풀어볼것) 
    (https://www.acmicpc.net/problem/1477, https://blog.encrypted.gg/975 맨 마지막)
    !! parametric search는 꽤 어려운 문제
    - parametric search문제라는 것을 눈치 채기 어렵다
    - parametric search혼자가 아닌 Dp, 그리디와 결합해서 나오는 경우 빈번
    - 내가 본 적 없는 parametric search문제가 나왔을때 99% 확률로 풀이를 못 잡아낸다
    - 바킹독님은 과감하게 배제하는 것도 괜찮아 보인다고 하심
    - 일단 공부해보자
    연습 문제5번 백준 1654를 통해 공부해보자


    연습문제
    1. 백준 1920(solve)
    2. 백준 10816(solve)
    3. 백준 18870(solve)
    4. 백준 2295(solve)
    - 2개의 값을 묶은 후 어느 한쪽의 값을 이분탐색으로 찾아서 시간복잡도를 낮추는 아이디어는 이분탐색 관련 응용문제에서 핵심적으로 많이 나온다
    5. 백준 1654
    - parametric search 의 대표 문제격

*/