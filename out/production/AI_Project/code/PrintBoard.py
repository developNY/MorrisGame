import math
import sys


def print_morris_board(board):
    size = 48
    COLS = size
    ROWS = (size // 4) + 1
    output = [[' '] * COLS for _ in range(ROWS)]

    for i in range(COLS):
        output[0][i] = '-'
        output[ROWS - 1][i] = '-'
        if COLS * (1 / 6) <= i <= COLS * (5 / 6):
            output[int(ROWS * (1 / 6))][i] = "-"
            output[int(ROWS * (5 / 6))][i] = "-"
        if COLS * (2 / 6) <= i <= COLS * (4 / 6):
            output[int(ROWS * (2 / 6))][i] = "-"
            output[int(ROWS * (4 / 6))][i] = "-"
        if i < COLS * (2 / 6) or i > COLS * (4 / 6):
            output[int(ROWS * (3 / 6))][i] = "-"

    for i in range(ROWS):
        output[i][0] = "|"
        output[i][COLS - 1] = "|"
        if int(ROWS * (1 / 6)) <= i < ROWS * (5 / 6):
            output[i][int(COLS * (1 / 6))] = "|"
            output[i][int(COLS * (5 / 6))] = "|"
        if int(ROWS * (2 / 6)) <= i < ROWS * (4 / 6):
            output[i][int(COLS * (2 / 6))] = "|"
            output[i][int(COLS * (4 / 6))] = "|"
        if i < ROWS * (2 / 6):
            output[i][int(COLS * 0.5)] = "|"
        if 0 < i <= int(ROWS * (2 / 6)):
            output[i][math.ceil(COLS * (1 / ROWS) * i)] = "\\"
            output[i][int(COLS - (COLS * (1 / ROWS) * i))] = "/"
        if int(ROWS * (4 / 6)) <= i < ROWS - 1:
            output[i][math.ceil(COLS * (1 / ROWS) * i) + 3] = "\\"
            output[i][int(COLS - (COLS * (1 / ROWS) * i)) - 3] = "/"

    if board[0].lower() != "x":
        output[ROWS - 1][0] = board[0]
    if board[1].lower() != "x":
        output[ROWS - 1][COLS - 1] = board[1]
    if board[2].lower() != "x":
        output[int(ROWS * (5 / 6))][int(COLS * (1 / 6))] = board[2]
    if board[3].lower() != "x":
        output[int(ROWS * (5 / 6))][int(COLS * (5 / 6))] = board[3]
    if board[4].lower() != "x":
        output[int(ROWS * (4 / 6))][int(COLS * (2 / 6))] = board[4]
    if board[5].lower() != "x":
        output[int(ROWS * (4 / 6))][int(COLS * (4 / 6))] = board[5]
    if board[6].lower() != "x":
        output[int(ROWS * 0.5)][0] = board[6]
    if board[7].lower() != "x":
        output[int(ROWS * 0.5)][int(COLS * (1 / 6))] = board[7]
    if board[8].lower() != "x":
        output[int(ROWS * 0.5)][int(COLS * (2 / 6))] = board[8]
    if board[9].lower() != "x":
        output[int(ROWS * 0.5)][int(COLS * (4 / 6))] = board[9]
    if board[10].lower() != "x":
        output[int(ROWS * 0.5)][int(COLS * (5 / 6))] = board[10]
    if board[11].lower() != "x":
        output[int(ROWS * 0.5)][COLS - 1] = board[11]
    if board[12].lower() != "x":
        output[int(ROWS * (2 / 6))][int(COLS * (2 / 6))] = board[12]
    if board[13].lower() != "x":
        output[int(ROWS * (2 / 6))][int(COLS * (3 / 6))] = board[13]
    if board[14].lower() != "x":
        output[int(ROWS * (2 / 6))][int(COLS * (4 / 6))] = board[14]
    if board[15].lower() != "x":
        output[int(ROWS * (1 / 6))][int(COLS * (1 / 6))] = board[15]
    if board[16].lower() != "x":
        output[int(ROWS * (1 / 6))][int(COLS * (3 / 6))] = board[16]
    if board[17].lower() != "x":
        output[int(ROWS * (1 / 6))][int(COLS * (5 / 6))] = board[17]
    if board[18].lower() != "x":
        output[0][0] = board[18]
    if board[19].lower() != "x":
        output[0][int(COLS * (3 / 6))] = board[19]
    if board[20].lower() != "x":
        output[0][COLS - 1] = board[20]


    res = []
    for row in output:
        res += row
        res.append('\n')
    print(''.join(res))


if __name__ == "__main__":
    print_morris_board(sys.argv[1])
