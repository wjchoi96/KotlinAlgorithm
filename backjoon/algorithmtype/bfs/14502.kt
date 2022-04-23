//gold5
/*
    네이버 카페 문제 50선 중 bfs 유형

    연구소

    연구소는 크기가 N×M인 직사각형
    연구소는 빈 칸, 벽으로 이루어져 있다
    일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다
    새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.

    0은 빈 칸, 1은 벽, 2는 바이러스

    벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다
    연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값을 구하는 프로그램을 작성하시오.

    3 <= N, M <= 8
    2 <= V <= 10
    빈 칸의 개수는 3개 이상
 */