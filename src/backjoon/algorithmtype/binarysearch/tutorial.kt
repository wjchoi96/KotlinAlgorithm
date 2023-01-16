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

    ## Upper, Lower Bound
    https://blog.naver.com/bestmaker0290/220820005454
    https://jackpot53.tistory.com/33

    # Lower Bound
    : 이분탐색에서 파생된 것으로, 원하는 K값 이상의 값이 처음 나오는 위치를 찾는 과정
    - mid값이 원하는 값보다 크거나 같을 경우, end값을 mid로 설정
    - mid값이 원하는 값보다 작다면 start를 mid+1로 설정
    - end가 원하는 값보다 크거나 같은 경우를 보관하고 있으므로 end를 리턴
    - 또는 반복의 조건인 start<end를 벗어난 경우이므로 start을 리턴해도 됨
        - 반복의 조건을 <=로 한경우 start-1, end를 리턴

    # Upper Bound
    : 이분탐색에서 파생된 것으로, 원하는 K값을 초과한 값이 처음 나오는 위치를 찾는 과정
    - mid값이 원하는 값보다 큰 경우, end를 mid로 설정
    - mid값이 원하는 값보다 작거나 같은 경우, start를 mid+1로 설정
    - end가 원하는 값을 초과하는 경우를 보관하고 있으니 end를 리턴
    - 또는 반복의 조건인 start<end를 벗어난 경우이므로 start을 리턴해도 됨



    연습문제
    1. 백준 1920(solve)
    2. 백준 10816(solve)
    3. 백준 18870(solve)
    4. 백준 2295(solve)
    - 2개의 값을 묶은 후 어느 한쪽의 값을 이분탐색으로 찾아서 시간복잡도를 낮추는 아이디어는 이분탐색 관련 응용문제에서 핵심적으로 많이 나온다
    5. 백준 1654(solve)
    - parametric search 의 대표 문제격
    6. 백준 1477(solve)
    - 잘못된 그리디 예제 중 parametric search 유형이라고 소개 된 문제

    # https://www.crocus.co.kr/1000 에서 제시한 Parametric Search 연습 문제
    7. 백준 2110(solve)
    8. 백즌 2613(solve)
    - 난이도가 생각보다 높아서 유형을 봤는데 dp or 그리디 or 이분탐색 이라 한다
    - 이분탐색적 코드는 구현을 잘 하였는데, 문제의 조건에 대한 구현을 잘 하지 못하였고, 정답코드를 보고도 90%정도만 이해가는 상태

    # 네이버 카페 문제50선 중 이분탐색 유형 문제
    9. 백준 10815(solve)
    10. 백준 1756(solve)
    11. 백준 1939(solve)

    # 백준 이분탐색 유형 문제
    12. 백준 6236(solve)

*/